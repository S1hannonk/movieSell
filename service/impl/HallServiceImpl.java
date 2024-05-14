package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.po.SpecialHall;
import movieSell.mapper.HallMapper;
import movieSell.service.HallService;
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
public class HallServiceImpl implements HallService {
    @Resource
    private HallMapper hallMapper;
    @Override
    @RedisCache( duration = 60 * 60)
    public List<SpecialHall> selectAll() {
        return hallMapper.selectList(null);
    }
}
