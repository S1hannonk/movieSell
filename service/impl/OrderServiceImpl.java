package movieSell.service.impl;

import com.alibaba.fastjson.JSON;
import movieSell.bean.bo.OrderAddBo;
import movieSell.bean.bo.Site;
import movieSell.bean.bo.SiteChooseBo;
import movieSell.service.OrderService;
import movieSell.util.RedisUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public boolean add(OrderAddBo orderAddBo, String authorization) {
        //步骤一：验证订单中的所有座位
        //遍历 订单中选择的每一个座位对象
        for(Site site: orderAddBo.getSites()){
            //对 当前遍历到的座位 验证座位信息
            //当前site组合选座对象模型 拼接字符串
            SiteChooseBo siteChooseBo = new SiteChooseBo();
            siteChooseBo.setWatchTimeId(orderAddBo.getWatchTimeId());
            siteChooseBo.setSite(site);
            String key = "Site-Frozen-" + JSON.toJSONString(siteChooseBo);
            if((!redisUtil.hasKey(key)) || (!redisUtil.get(key).equals(authorization))){
                return false;
            }
        }
        //可以生成订单
        //步骤二：发送消息到kafka消息队列中的order话题中
        kafkaTemplate.send("order",JSON.toJSONString(orderAddBo),authorization);
        return true;
    }
}
