package movieSell.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import movieSell.bean.bo.OrderAddBo;
import movieSell.bean.po.*;
import movieSell.mapper.OrderMapper;
import movieSell.util.ESResponse;
import movieSell.util.ESUtil;
import movieSell.util.RedisUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Component
public class OrderConsumer {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ESUtil esUtil;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 添加订单监听反法
     * @param record
     * @param ack
     */
    @KafkaListener(topics = "order",groupId = "group-01")
    public void add(ConsumerRecord<String,String> record, Acknowledgment ack){
        SearchSourceBuilder searchSourceBuilder = null;
        BoolQueryBuilder boolQueryBuilder = null;

        //步骤一：接收消息队列中的order话题进行解析
        //将key解析成 订单添加业务模型对象
        OrderAddBo orderAddBo = JSONObject.parseObject(record.key(),OrderAddBo.class);
        //将消息的value解析成 订单天界的用户身份
        String authorization = record.value();
        //从redis中通过登录认证获取用户信息
        UserInfo userInfo = (UserInfo) redisUtil.get(authorization);
        //根据订单的场次编号 获取场次实体模型对象
        searchSourceBuilder = new SearchSourceBuilder();
        boolQueryBuilder= new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("wt_id",orderAddBo.getWatchTimeId()));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<WatchTimes> watchTimesESResponse = esUtil.search("watch_times", searchSourceBuilder, WatchTimes.class);
        WatchTimes watchTimes = watchTimesESResponse.getData().get(0);
        //根据 场次实体对象中的影院编号 获取影院实体模型
        searchSourceBuilder = new SearchSourceBuilder();
        boolQueryBuilder= new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("cma_id",watchTimes.getCmaId()));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<Cinema> cinemaESResponse = esUtil.search("cinema", searchSourceBuilder, Cinema.class);
        Cinema cinema = cinemaESResponse.getData().get(0);
        //根据 场次实体模型获取电影信息
        searchSourceBuilder = new SearchSourceBuilder();
        boolQueryBuilder= new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("film_id",watchTimes.getFilmId()));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<Film> filmESResponse = esUtil.search("film", searchSourceBuilder, Film.class);
        Film film = filmESResponse.getData().get(0);

        //步骤二：将接收到的订单消息，写入到mysql数据库中orders订单表中
        Orders orders = new Orders();
        //设置订单编号
        orders.setOrderNo(UUID.randomUUID().toString());
        //设置时间
        orders.setOrderTime(new Date());
        //设置 订购用户编号
        orders.setOrderUserId(userInfo.getUserId());
        //设置昵称
        orders.setOrderUserNick(userInfo.getUserNickName());
        //设置 影院编号
        orders.setOrderCinemaId(cinema.getCmaId());
        //设置影院名称
        orders.setOrderCinemaName(cinema.getCmaName());
        //设置场次信息
        orders.setOrderWtId(watchTimes.getWtId());
        orders.setOrderWdDate(watchTimes.getWdDate());
        orders.setOrderWtBegintime(watchTimes.getWtBegintime());
        orders.setOrderWtEndtime(watchTimes.getWtEndtime());
        orders.setOrderWtHalls(watchTimes.getWtHalls());
        //设置花费
        orders.setOrderCost(
                new BigDecimal (watchTimes.getWtCost().doubleValue() * orderAddBo.getSites().size()) );

        //设置订单电影信息
        orders.setOrderFilmId(film.getFilmId());
        orders.setOrderFilmName(film.getFilmName());
        //设置座位列表
        orders.setOrderSites(JSON.toJSONString(orderAddBo.getSites()));
        //设置 订单的作为状态是否批次同步 0：未同步 1；已同步
        orders.setOrderIsUse((byte) 0);
        //订单的状态
        orders.setOrderState((byte) 1);
        /////////////////////////////////////////////////////////////////
        //添加订单实体模型到mysql中
        int insert = orderMapper.insert(orders);
        //判断数据库受影响行数
        if(insert > 0){
            //1 行说明成功
            ack.acknowledge();
        }
    }
}
