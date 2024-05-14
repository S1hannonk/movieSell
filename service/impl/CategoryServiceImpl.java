package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.po.Category;
import movieSell.mapper.CategoryMapper;
import movieSell.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    @RedisCache(duration = 60 * 60)
    public List<Category> selectAll() {
        return categoryMapper.selectList( null);
    }
}
