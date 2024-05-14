package movieSell.bean.vo;

import movieSell.bean.po.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:影片视图模型
 * FilmVO => 视图模型类  （DHTML 混合开发）MVC模式中的View试图截面是同的模型数据
 * FilmDTO => 数据转换模型类 前后端分离WebAPI接口（数据json数据格式）
 */
public class FilmVo {
    //影片实体对象
    private Film film;
    //观影日期试图模型对象的集合
    private List<WatchDateVo>  watchDateVoList = new ArrayList<>();

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<WatchDateVo> getWatchDateVoList() {
        return watchDateVoList;
    }

    public void setWatchDateVoList(List<WatchDateVo> watchDateVoList) {
        this.watchDateVoList = watchDateVoList;
    }
}
