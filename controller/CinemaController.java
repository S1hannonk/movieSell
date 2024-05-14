package movieSell.controller;

import movieSell.bean.bo.CinemaSearchBo;
import movieSell.bean.po.Cinema;
import movieSell.bean.po.Film;
import movieSell.bean.vo.FilmVo;
import movieSell.service.BrandService;
import movieSell.service.CinemaService;
import movieSell.service.FilmService;
import movieSell.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projectName:
 *影院控制器
 * @author: Shannon
 * description:
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private HallService hallService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private FilmService filmService;

    @PostMapping
    public Object list(CinemaSearchBo cinemaSearchBo){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",200);
        responseBody.put("message","ok");
        HashMap<String, Object> data = new HashMap<>();
        //业务方法
        //影院品牌
        data.put("cinema",brandService.selectAll());
        //影院类型
        data.put("SpecialHall",hallService.selectAll());
        //响应数据中添加满足条件的影院
        data.put("searchList",cinemaService.searchList(cinemaSearchBo));
        data.put("CinemaSearchBo" ,cinemaSearchBo);
        responseBody.put("data",data);
        return responseBody;
    }
    @GetMapping ("/{cinemaId}")
    public Object one(@PathVariable Integer cinemaId){
        Map<String, Object> responseBody = new HashMap<>();
        Cinema cinema = cinemaService.searchOne(cinemaId);
        if(cinema == null){
            responseBody.put("code",500);
            responseBody.put("message","该资源不存在");
        }else {
            responseBody.put("code", 200);
            responseBody.put("message", "ok");
            //根据 影院编号查询影院实体模型对象
            //添加影院实体模型对象
            Map<String, Object> data = new HashMap<>();
            data.put("Cinema",cinema);
            //添加影片放映计划模型对象
            data.put("filmVoList",filmService.searchFilmVo(cinemaId));
        }
        return responseBody;
    }
}
