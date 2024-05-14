package movieSell.controller;

import movieSell.bean.po.WatchTimes;
import movieSell.service.CinemaService;
import movieSell.service.FilmService;
import movieSell.service.WatchTinesService;
import movieSell.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/watchTimes")
public class WatchTimesController {
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private WatchTinesService watchTinesService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private FilmService filmService;
    /**
     *场次信息接口
     * @param watchTimesId
     * @return
     */
    @GetMapping("/watchTimesId")
    public Object one(
            @PathVariable String watchTimesId,
            @RequestHeader String authorization){
        Map<String, Object> responseBody = new HashMap<>();
        //登录身份认证 => reids
        if(redisUtil.hasKey(authorization)){
            //登录身份验证成功
            //根据场次信息查询场次
            WatchTimes watchTimes = watchTinesService.selectOne(watchTimesId);
            if(watchTimes == null){
                responseBody.put("code", 500);
                responseBody.put("message", "该场次不存在");
            }else {
                responseBody.put("code", 200);
                responseBody.put("message", "ok");
                Map<String, Object> data = new HashMap<>();
                //响应数据 场次信息
                data.put("watchTimes",watchTimes);
                // 影院信息
                data.put("Cinema",cinemaService.searchOne(watchTimes.getCmaId()));
                //电影信息
                data.put("Film",filmService.searchOne(watchTimes.getFilmId()));
                responseBody.put("data",data);
            }
        }else {
            responseBody.put("code", 401);
            responseBody.put("message", "Unauthorized");

        }
        return responseBody;

    }
}
