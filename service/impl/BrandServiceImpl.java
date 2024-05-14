package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.po.Brand;
import movieSell.bean.po.Cinema;
import movieSell.mapper.BrandMapper;
import movieSell.service.BrandService;
import movieSell.util.ESUtil;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    @Override
    @RedisCache(duration = 60 * 60)
    public List<Brand> selectAll() {
        return brandMapper.selectList(null);
    }
}
