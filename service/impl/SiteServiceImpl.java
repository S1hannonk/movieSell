package movieSell.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import movieSell.bean.bo.SiteChooseBo;
import movieSell.bean.po.Orders;
import movieSell.mapper.OrderMapper;
import movieSell.service.SiteService;
import movieSell.util.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Service
public class SiteServiceImpl implements SiteService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private RedissonClient redissonClient;


    /**
     * @param siteChooseBo
     * @param authorization
     * @return 查看redis中是否已选座位
     */
    @Override
    public boolean choose(SiteChooseBo siteChooseBo, String authorization) {
        //步骤一：判断座位是否由冻结信息
        String key = "Site_Frozen-" + JSON.toJSONString(siteChooseBo);
        if(redisUtil.hasKey(key)){
            //redis中存在冻结信息
            return false;
        }
        //步骤二：判断该座位是否在mysql中orders数据库中存在订单信息
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = Wrappers.lambdaQuery();
        ordersLambdaQueryWrapper.eq(Orders::getOrderWtId, siteChooseBo.getWatchTimeId());
        ordersLambdaQueryWrapper.like(Orders::getOrderSites,"%" + JSON.toJSONString(siteChooseBo.getSite()) + "%");
        List<Orders> ordersList = orderMapper.selectList(ordersLambdaQueryWrapper);
        if(ordersList.size() > 0){
            //订单有该座位
            return false;
        }
        //步骤三：争夺分布式锁
        RLock lock = redissonClient.getLock("lock-" + key);
        lock.lock();
        //步骤四：生成座位冻结信息
        boolean result = redisUtil.set(key, authorization, 60 * 5);
        lock.unlock();
        return result;
    }
}
