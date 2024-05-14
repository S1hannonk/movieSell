package movieSell.service;

import movieSell.bean.po.Brand;
import movieSell.bean.po.Cinema;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface BrandService {
    List<Brand> selectAll();
}
