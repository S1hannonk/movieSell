package movieSell.service;

import movieSell.bean.bo.CinemaSearchBo;
import movieSell.bean.po.Cinema;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface CinemaService {
    /**
     *根据条件搜索影院信息
     * @param cinemaSearchBo
     * @return
     */
    List<Cinema> searchList(CinemaSearchBo cinemaSearchBo);

    /**
     * @param cinemaId
     * @return
     */
    Cinema searchOne(Integer cinemaId);
}
