package movieSell.aop;

import com.alibaba.fastjson.JSON;
import movieSell.aop.annotation.RedisCache;
import movieSell.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description:Redis缓存切面类
 */
@Component
@Aspect
public class RedisCacheAspect {
    //依赖项
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RedissonClient redissonClient;

    /**
     * @param joinPoint  连接点
     * @param redisCache
     * @return 响应报文
     */
    @Around("@annotation(redisCache)")
    public Object around(
            ProceedingJoinPoint joinPoint, RedisCache redisCache)  {
        //步骤1：判断Redis中是否有缓存数据
        //1.1 组成Redis 中存储缓存数据的key（缓存数据的颗粒度）
        //     key的颗粒度：包含方法+ 参数 = 方法的签名 signature = 包名.类名.方法名.(形参列表)
        //实例化map对象，装箱key 包含的数据
        RLock lock = null;
        try {
            Map<String, Object> keyMap = new HashMap<>();
            String signature = joinPoint.getSignature().toString();
            //装箱方法名
            keyMap.put("method", signature);
            //装箱参数列表
            Object[] params = joinPoint.getArgs();
            keyMap.put("params", params);
            //对Map对象使用json序列化成key字符串
            String key = "Cache-" + JSON.toJSONString(keyMap);
            //1.2 获取Redis中的缓存数据
            Object cacheData = redisUtil.get(key);
            //1.3判断是否命中
            if (cacheData != null) {
                //[缓存命中] 直接响应缓存数据
                return cacheData.toString().equals("null") ? null : cacheData;
            }
            //缓存未命中
            //缓存击穿 => 争夺分布式锁
            //生成锁对象
            lock = redissonClient.getLock("lock-" + key);
            //加锁
            lock.lock();
            //判断混村数据cacheData是否为空
            cacheData = redisUtil.get(key);
            if (cacheData != null) {
                //[缓存命中] 直接响应缓存数据
                lock.unlock();
                return cacheData.toString().equals("null") ? null : cacheData;
            }
//        //---------------------------------------------------------------------
            Object value = joinPoint.proceed();
            //缓存穿透 => 判断MySql数据库中查询的数据是否为null
            //缓存雪崩 =>在缓存数据的有效期中，加入随即策略
            redisUtil.set(key,
                    value == null ? "null" : value,
                    value == null ? 3 * 10 : redisCache.duration() + (long)(Math.random() * redisCache.duration()/10));
            //解锁
            lock.unlock();
            return value;
        } catch (Throwable e){
            e.printStackTrace();
            return  null;
        }
    }

}
