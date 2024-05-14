package movieSell.service.impl;

import movieSell.bean.po.Comment;
import movieSell.service.CommentService;
import movieSell.util.ESResponse;
import movieSell.util.ESUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
public class CommentServiceImpl implements CommentService {
    @Resource
    private ESUtil esUtil;

    /**
     * @param filmId
     * @return
     */
    @Override
    public List<Comment> searchListByFilmId(Integer filmId) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("film_id", filmId));
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort(
                "cmmt_createtime",
                SortOrder.DESC);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        ESResponse<Comment> esResponse = esUtil.search("comment", searchSourceBuilder, Comment.class);
        return esResponse.getData();
    }
}
