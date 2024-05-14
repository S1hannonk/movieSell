package movieSell.controller;

import movieSell.bean.bo.OrderAddBo;
import movieSell.service.OrderService;
import movieSell.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OrderService orderService;

    /**
     * 生成订单接口
     * @param authorization 认证
     * @param orderAddBo 业务模型对象
     * @return 报文体
     */
    public Object add(@RequestHeader String authorization,
                      @RequestBody OrderAddBo orderAddBo){
        Map<String, Object> responseBody = new HashMap<>();
        if(redisUtil.hasKey(authorization)) {//成功
            //调用业务逻辑
            if(orderService.add(orderAddBo,authorization)){
                responseBody.put("code",200);
                responseBody.put("message","ok");
            }else {
                responseBody.put("code",500);
                responseBody.put("message","订单生成失败！请重新选择座位");
            }
        }else {//失败
            responseBody.put("code",401);
            responseBody.put("message","Unauthorized");
        }
            return responseBody;

    }
}
