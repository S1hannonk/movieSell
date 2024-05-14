package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.po.FilmRegion;
import movieSell.mapper.FilmMapper;
import movieSell.mapper.FilmRegionMapper;
import movieSell.service.FilmRegionService;
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
public class FilmRegionServiceImpl implements FilmRegionService {
    @Resource
    private FilmRegionMapper filmRegionMapper;

    @Override
    @RedisCache(duration = 60 * 60)
    public List<FilmRegion> selectAll() {
        return filmRegionMapper.selectList(null);
    }
}
