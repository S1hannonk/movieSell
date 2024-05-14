package movieSell.controller;

import movieSell.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public Object index(){
        //实例化响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        //设置响应报文体 业务编码
        responseBody.put("code","200");
        //响应报文体 业务信息
        responseBody.put("massage","ok");

        //实例化响应报文体业务数据
        Map<String,Object> data = new HashMap<>();
        //设置业务数据中的正在热映影片列表
        data.put("currentList",filmService.selectListByTypeId(1));
        //设置业务数据中的正在热映影片数
        data.put("currenCount",filmService.selectCountByTypeID(1));
        //设置业务数据中的即将上映影片列表
        data.put("nextList",filmService.selectListByTypeId(2));
        //设置业务数据中的即将上映影片数
        data.put("nextCount",filmService.selectCountByTypeID(2));
        //设置业务数据中的热播电影影片列表
        data.put("hotList",filmService.selectListByTypeId(3));
        //设置业务数据中的喜好排名电影
        data.put("favoriteList",filmService.selectListOrderBy("film_favorite"));
        //设置业务数据中的评分排名电影
        data.put("praiseList",filmService.selectListOrderBy("film_praise"));
        //响应报文体 数据
        responseBody.put("data",data);

        return responseBody;

    }
}
