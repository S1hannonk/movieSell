package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.bo.CinemaSearchBo;
import movieSell.bean.po.Cinema;
import movieSell.service.CinemaService;
import movieSell.util.ESResponse;
import movieSell.util.ESUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
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
public class CinemaServiceImpl implements CinemaService {
    @Resource
    private ESUtil esUtil;
    @Override
    @RedisCache(duration = 60*60)
    public List<Cinema> searchList(CinemaSearchBo cinemaSearchBo) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(cinemaSearchBo.getBrandId() != null)
        //影院品牌筛选
            boolQueryBuilder.must(QueryBuilders.termQuery("brand_id",cinemaSearchBo.getBrandId()));
        if(cinemaSearchBo.getSpecialHallId() != null)
        //影院类型筛选
            boolQueryBuilder.must(QueryBuilders.matchQuery("cma_halls_query",cinemaSearchBo.getSpecialHallId()));
        if(cinemaSearchBo.getServiceId() != null)
        //影院服务
            boolQueryBuilder.must(QueryBuilders.termQuery("cma_service_query",cinemaSearchBo.getServiceId()));
        if(cinemaSearchBo.getKeyWord() != null && !cinemaSearchBo.getKeyWord().isBlank())
        //搜索关键字
            boolQueryBuilder.must(QueryBuilders.matchQuery("cma_name",cinemaSearchBo.getKeyWord()));
        //添加
        searchSourceBuilder.query(boolQueryBuilder);
        if(cinemaSearchBo.getOrderColumn() != null && !cinemaSearchBo.getOrderColumn().isBlank()) {
            searchSourceBuilder.sort(
                    cinemaSearchBo.getOrderColumn(),
                    cinemaSearchBo.getOrderType() != null && cinemaSearchBo.getOrderType().equalsIgnoreCase("desc")?
                    SortOrder.DESC:SortOrder.ASC
            );
        }
        searchSourceBuilder.from(cinemaSearchBo.getStartIndex());
        searchSourceBuilder.size(cinemaSearchBo.getPageSize());
        //向ES发送请求
        ESResponse<Cinema> esResponse = esUtil.search("cinema", searchSourceBuilder, Cinema.class);
        //设置 查询命中总数
        cinemaSearchBo.setPageCount((int) esResponse.getResultCount());
        return esResponse.getData();
    }

    @Override
    @RedisCache(duration = 60*60)
    public Cinema searchOne(Integer cinemaId) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("cma_id",cinemaId));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<Cinema> esResponse = esUtil.search("cinema", searchSourceBuilder, Cinema.class);
        return esResponse.getResultCount() == 0?null:esResponse.getData().get(0);
    }
}
