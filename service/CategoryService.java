package movieSell.service;

import movieSell.bean.po.Category;
import movieSell.bean.po.Film;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface CategoryService {
    List<Category> selectAll();
}
