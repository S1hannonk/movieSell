package movieSell.service.impl;

import movieSell.aop.annotation.RedisCache;
import movieSell.bean.po.WatchTimes;
import movieSell.service.WatchTinesService;
import movieSell.util.ESResponse;
import movieSell.util.ESUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Service
public class WatchTimesServiceImpl implements WatchTinesService {
    @Resource
    private ESUtil esUtil;
    @Override
    @RedisCache(duration = 60*60)
    public WatchTimes selectOne(String watchTimeId) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("wt_id",watchTimeId));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<WatchTimes> watchTimesESResponse = esUtil.search("watch_times", searchSourceBuilder, WatchTimes.class);

        return watchTimesESResponse.getResultCount() == 0?null:watchTimesESResponse.getData().get(0);
    }
}
