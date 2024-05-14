package movieSell.service;

import movieSell.bean.po.SpecialHall;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */

public interface HallService {
    List<SpecialHall> selectAll();
}
