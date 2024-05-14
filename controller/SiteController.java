package movieSell.controller;

import movieSell.bean.bo.SiteChooseBo;
import movieSell.service.SiteService;
import movieSell.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SiteService siteService;
    /**
     * @param siteChooseBo 选座对象
     * @param authorization 登录认证
     * @return 响应报文体
     */
    @PostMapping
    public Object choose(@RequestBody SiteChooseBo siteChooseBo,
                         @RequestHeader String authorization){
        Map<String, Object> responseBody = new HashMap<>();
        if(redisUtil.hasKey(authorization)){//成功
            //调用业务逻辑
            if(siteService.choose(siteChooseBo, authorization)){
                responseBody.put("code" ,200);
                responseBody.put("message" ,"ok");
            }else {
                responseBody.put("code" ,500);
                responseBody.put("message" ,"该座位已被占用！请重新选择");
            }
        }else {//失败
            responseBody.put("code",401);
            responseBody.put("message","Unauthorized");
        }
        return null;
    }
}
