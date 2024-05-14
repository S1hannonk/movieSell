package movieSell.controller;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.bo.FilmSearchBo;
import movieSell.bean.po.Category;
import movieSell.bean.po.Film;
import movieSell.service.CategoryService;
import movieSell.service.CommentService;
import movieSell.service.FilmRegionService;
import movieSell.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description: 影片功能控制器
 */
@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FilmRegionService filmRegionService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private CommentService commentService;

    /**
     * @param filmSearchBo 影片搜索业务模型对象
     * @return 响应报文体
     */
    @PostMapping
    public Object List(FilmSearchBo filmSearchBo) {
        //实例化响应报文体
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", 200);
        responseBody.put("massage", "ok");
        //实例化响应数据
        Map<String, Object> data = new HashMap<>();
        //响应数据中添加所有的影片类型
        data.put("categoryList", categoryService.selectAll());
        //响应数据中添加所有 的拍摄地
        data.put("filmRegionList", filmRegionService.selectAll());
        //响应数据中添加满足筛选条件、搜索条件、排序条件、分页条件的影片
        data.put("filmList", filmService.searchList(filmSearchBo));
        //响应数据中 添加 搜索业务模型对象
        data.put("filmSearchBo", filmSearchBo);
        responseBody.put("data", data);
        //返回响应报文体
        return responseBody;
    }

    /**
     * 影片详情页面接口
     *
     * @param filmId 要查询的影片编号
     * @return
     */
    @GetMapping("/{id}")
    public Object one(@PathVariable("id") Integer filmId) {
        //实例化响应报文体
        Map<String, Object> responseBody = new HashMap<>();
        //调用 业务逻辑层 查询影片信息
        Film film = filmService.searchOne(filmId);
        //判断影片是否存在
        if (film == null) {
            responseBody.put("code", "500");
            responseBody.put("message", "资源不存在");
        } else {
            responseBody.put("code", "200");
            responseBody.put("message", "ok");
            //实例化响应数据对象
            Map<String, Object> data = new HashMap<>();
            //添加影片信息
            data.put("film", film);
            //添加影片评论
            data.put("commentList", commentService.searchListByFilmId(filmId));
            responseBody.put("data", data);
        }
        return responseBody;
    }
}
