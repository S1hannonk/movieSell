package movieSell.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.alibaba.fastjson.JSON;
import javax.annotation.Resource;

@Component
public class ESUtil {

    @Resource
    public RestHighLevelClient restClient;

    public boolean indexExist(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return restClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    public IndexResponse insertOrUpdateOne(String index, EsEntity entity) {
        IndexRequest request = new IndexRequest(index);
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            return restClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BulkResponse insertBatch(String index, List<EsEntity> list) {
        BulkRequest request = new BulkRequest();
        for (EsEntity item : list) {
            String _json = JSON.toJSONString(item.getData());
            String _id = item.getId();
            IndexRequest indexRequest = new IndexRequest(index).id(_id).source(_json, XContentType.JSON);
            request.add(indexRequest);
        }
        try {
            return restClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> ESResponse<T> search(String index, SearchSourceBuilder searchSourceBuilder, Class<T> resultClass) {
        SearchRequest request = new SearchRequest(index);
        request.source(searchSourceBuilder);
        try {

            // 实例化 ESResponse 对象
            ESResponse<T> esResponse = new ESResponse<>();

            SearchResponse response = restClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits1 = response.getHits();

            // 设置 总命中数
            long resultCount = hits1.getTotalHits().value;  // 总命中数
            esResponse.setResultCount( resultCount );

            SearchHit[] hits2 = hits1.getHits();
            List<T> retList = new ArrayList<>(hits2.length);
            for (SearchHit hit : hits2) {
                String strJson = hit.getSourceAsString();
                retList.add(JSON.parseObject(strJson, resultClass));
            }

            // 设置 实体模型对象集合
            esResponse.setData( retList );

            // 返回 ESResponse 对象
            return esResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public AcknowledgedResponse deleteIndex(String index) {
        try {
            IndicesClient indicesClient = restClient.indices();
            DeleteIndexRequest request = new DeleteIndexRequest(index);
            AcknowledgedResponse response = indicesClient.delete(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BulkByScrollResponse deleteByQuery(String index, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            BulkByScrollResponse response = restClient.deleteByQuery(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DeleteResponse deleteOne( String index , String id ){
        DeleteRequest request = new DeleteRequest(index,id);
        DeleteResponse response = null;
        try {
            response = restClient.delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public <T> BulkResponse deleteBatch(String index, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        for (T t : idList) {
            request.add(new DeleteRequest(index, t.toString()));
        }
        try {
            BulkResponse response = restClient.bulk(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}