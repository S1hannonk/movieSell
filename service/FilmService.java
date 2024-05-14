package movieSell.service;

import jdk.jfr.FlightRecorder;
import movieSell.bean.bo.FilmSearchBo;
import movieSell.bean.po.Film;
import movieSell.bean.vo.FilmVo;
import org.apache.ibatis.annotations.Param;


import java.io.File;
import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:影片业务逻辑层方法
 */

public interface FilmService {
    List<Film> selectListByTypeId(Integer typeId);
    Integer selectCountByTypeID(Integer typeId);
    List<Film> selectListOrderBy( String orderColumn);
    List<Film> searchList(FilmSearchBo filmSearchBo);
    Film searchOne(Integer filmID);
    List<FilmVo> searchFilmVo(Integer cinemaId);
}
