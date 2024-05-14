package movieSell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import movieSell.aop.annotation.RedisCache;
import movieSell.bean.bo.FilmSearchBo;
import movieSell.bean.po.CinemaFilmRel;
import movieSell.bean.po.Film;
import movieSell.bean.po.WatchTimes;
import movieSell.bean.vo.FilmVo;
import movieSell.bean.vo.WatchDateVo;
import movieSell.mapper.CinemaFilmRelMapper;
import movieSell.mapper.FilmMapper;
import movieSell.service.FilmService;
import movieSell.util.ESResponse;
import movieSell.util.ESUtil;
import movieSell.util.RedisUtil;
import org.apache.commons.collections.BoundedCollection;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@Service
public class FilmServiceImpl implements FilmService {
    @Resource
    private FilmMapper filmMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ESUtil esUtil;
    @Resource
    private CinemaFilmRelMapper cinemaFilmRelMapper;

//    /**
//     * @param typeId
//     * @return 返回查询列表
//     */
//    @Override
//    public List<Film> selectListByTypeId(Integer typeId) {
//        //步骤1：判断Redis中是否有缓存数据
//        //1.1 组成Redis 中存储缓存数据的key（缓存数据的颗粒度）
//        //     key的颗粒度：包含方法+ 参数 = 方法的签名 signature = 包名.类名.方法名.(形参列表)
//        //实例化map对象，装箱key 包含的数据
//        Map<String, Object> keyMap = new HashMap<>();
//        //装箱方法名
//        keyMap.put("method", "movieSell.service.impl.FileServiceImpl.selectListByTypeId");
//        //装箱参数列表
//        keyMap.put("params", new Object[]{typeId});
//        //对Map对象使用json序列化成key字符串
//        String key = "Cache-" + JSON.toJSONString(keyMap);
//        //1.2 获取Redis中的缓存数据
//        Object value = redisUtil.get(key);
//        //1.3判断是否命中
//        if (value != null) {
//            //[缓存命中] 直接响应缓存数据
//            return (List<Film>) value;
//        }
//        //---------------------------------------------------------------------
//        //步骤2 ： 去MySql中查咨寻
//        //调用mapper层 持久层
//        List<Film> filmList = filmMapper.selectListByTypeId(typeId);
//        //----------------------------------------------------------------------
//        //步骤3：将mysql中数据生成缓存数据到redis中
//        redisUtil.set(key, filmList, 60 * 10);
//        //----------------------------------------------------------------
//        //步骤4：将mysql中数据进行响应
//        //返回数据
//        return filmList;
//    }

    /**
     * @param typeId
     * @return 返回查询列表
     */
    @Override
    @RedisCache(duration = 60 * 10)
    public List<Film> selectListByTypeId(Integer typeId) {
        //调用mapper层 持久层
        List<Film> filmList = filmMapper.selectListByTypeId(typeId);
        //返回数据
        return filmList;
    }

    /**
     * @param typeId
     * @return 返回查询值
     */
    @Override
    @RedisCache(duration = 60 * 10)
    public Integer selectCountByTypeID(Integer typeId) {
        Integer count = filmMapper.selectCountByTypeID(typeId);
        return count;
    }

    /**
     * @param orderColumn
     * @return 返回查询列表
     */
    @Override
    @RedisCache(duration = 60 * 10)
    public List<Film> selectListOrderBy(String orderColumn) {
        List<Film> filmList = filmMapper.selectListOrderBy(orderColumn);
        return filmList;
    }

    /**
     * 根据筛选条件、搜索条件、排序条件、分页条件从es筛选文档
     *
     * @param filmSearchBo 影片搜索业务模型对象
     * @return 影片实体模型对象集合
     */
    @Override
    @RedisCache(duration = 60 * 60)
    public List<Film> searchList(FilmSearchBo filmSearchBo) {
        //步骤一 ： 实例化资源查询构建器SearchSourceBuilder
        //包含了查询的筛选条件、搜索条件、排序条件、分页条件
        // 类似JDBC Statement 命令执行对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        ///////////////////////////////////////////////////////////////////////
        //步骤二：实例化逻辑查询构建器BoolQueryBuilder
        //       包含了查询的筛选条件、搜索条件
        //  类似SQL中where子句
        //    boolQueryBuilder.must() 逻辑与关系 类似&&  and
        //    boolQueryBuilder.should() 逻辑或 ||  or
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //QueryBuilders.termQuery() 与存储原文档进行匹配
        //QueryBuilders.matchQuery() 与存储文档的索引词条进行匹配(分词索引)
        //2.1 添加 影片类型筛选条件
        if (filmSearchBo.getCategoryID() != null)
            boolQueryBuilder.must(QueryBuilders.matchQuery("film_cate_query", filmSearchBo.getCategoryID()));
        //2.2 添加电影拍摄地筛选条件
        if (filmSearchBo.getRegionID() != null)
            boolQueryBuilder.must(QueryBuilders.matchQuery("film_region_query", filmSearchBo.getRegionID()));
        //2.3 添加影片上映年份 筛选条件
        if (filmSearchBo.getYear() != null && !filmSearchBo.getYear().isBlank())
            boolQueryBuilder.must(QueryBuilders.termQuery("film_year", filmSearchBo.getYear()));
        //2.4 搜索关键字条件
        if (filmSearchBo.getKeyWord() != null && !filmSearchBo.getKeyWord().isBlank())
            boolQueryBuilder.must(QueryBuilders.matchQuery("film_name", filmSearchBo.getKeyWord()));
        //2.5 将逻辑查询构建器BoolQueryBuilder放入资源查询构建器SearchSourceBuilder
        searchSourceBuilder.query(boolQueryBuilder);
        ///////////////////////////////////////////////////////////////////
        //步骤三：资源查询构建器SearchSourceBuilder：配置 排序条件
        if (filmSearchBo.getOrderColumn() != null && filmSearchBo.getOrderColumn().isBlank())
            searchSourceBuilder.sort(
                    filmSearchBo.getOrderColumn(),
                    filmSearchBo.getOrderType() != null && filmSearchBo.getOrderType().equalsIgnoreCase("desc") ?
                            SortOrder.DESC :
                            SortOrder.ASC
            );
        ///////////////////////////////////////////////////////////////////
        //步骤四：资源查询构建器SearchSourceBuilder配置 分页条件 limit x，y
        searchSourceBuilder.from(filmSearchBo.getStartIndex());
        searchSourceBuilder.size(filmSearchBo.getPageSize());
        ////////////////////////////////////////////////////////////////////
        //步骤五：向ES搜索引擎 发送查询请求 得到响应
        ESResponse<Film> esResponse = esUtil.search("film", searchSourceBuilder, Film.class);
        ////////////////////////////////////////////////////////////////////
        //步骤六：将查询命中的文档数 设置到bo对象中
        filmSearchBo.setResultCount((int) esResponse.getResultCount());
        //步骤七：返回 满足条件的文档 映射成的 实体模型对象集合
        return esResponse.getData();
    }

    /**
     * 根据影片ID从ES查找影片信息
     *
     * @param filmID
     * @return
     */
    @Override
    @RedisCache(duration = 60 * 60)
    public Film searchOne(Integer filmID) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("film_id", filmID));
        searchSourceBuilder.query(boolQueryBuilder);
        ESResponse<Film> esResponse = esUtil.search("film", searchSourceBuilder, Film.class);
        return esResponse.getResultCount() == 0 ? null : esResponse.getData().get(0);
    }

    @Override
    @RedisCache(duration = 60 * 60)
    public List<FilmVo> searchFilmVo(Integer cinemaId) {
        //声明 资源查询器构建器
        SearchSourceBuilder searchSourceBuilder = null;
        //声明逻辑查询构建器
        BoolQueryBuilder boolQueryBuilder = null;
        List<FilmVo> filmVoList = new ArrayList<>();
        //根据影院编号查询正在上映电影
        LambdaQueryWrapper<CinemaFilmRel> cinemaFilmRelWrapper = Wrappers.lambdaQuery();
        cinemaFilmRelWrapper.eq(CinemaFilmRel::getCmaId,cinemaId);
        List<CinemaFilmRel> cinemaFilmRelList = cinemaFilmRelMapper.selectList(cinemaFilmRelWrapper);
        //遍历该影院上映电影id查询相关电影
        for(CinemaFilmRel cinemaFilmRel: cinemaFilmRelList){
            //实例化 影片视图模型对对象
            FilmVo filmVo = new FilmVo();
            //查询相关影片
            searchSourceBuilder = new SearchSourceBuilder();
            boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.must(QueryBuilders.termQuery("film_id",cinemaFilmRel.getFilmId()));
            searchSourceBuilder.query(boolQueryBuilder);
            ESResponse<Film> filmESResponse = esUtil.search("film", searchSourceBuilder, Film.class);
            //电影对象赋值
            filmVo.setFilm(filmESResponse.getData().get(0));
            ///////////////////////////////////////////////////////////////////////////////
            //根据 影院编号影片编号搜索该影院 该影片 的场次信息
            searchSourceBuilder = new SearchSourceBuilder();
            boolQueryBuilder= new BoolQueryBuilder();
            boolQueryBuilder.must(QueryBuilders.termQuery("cam_id",cinemaId));
            boolQueryBuilder.must(QueryBuilders.termQuery("film_id",cinemaFilmRel.getFilmId()));
            searchSourceBuilder.query(boolQueryBuilder);
            searchSourceBuilder.sort(
                    "wt_begintime",SortOrder.ASC);

            ESResponse<WatchTimes> watchTimesResponse = esUtil.search("watch_times", searchSourceBuilder, WatchTimes.class);
            List<WatchTimes> watchTimesList = watchTimesResponse.getData();
            //实例化观影日期
            Set<String> watchTimeDate =  new HashSet<>();
            //遍历场次信息 筛选场次日期
            for(WatchTimes watchTimes:watchTimesList){
                watchTimeDate.add(watchTimes.getWdDate());
            }
            //遍历 观影日期集合
            for(String date : watchTimeDate){
                //根据当前遍历到的观影日期 实例化 观影日期视图模型对象
                WatchDateVo watchDateVo = new WatchDateVo();
                //添加观影日期
                watchDateVo.setDate(date);
                ////////////////////////////////////////////////////////////////
                //对查询道德watchTimeList 再次遍历选出当前日期下的所有时间安排
                for(WatchTimes watchTimes : watchTimesList){
                    //判断
                    if(watchTimes.getWdDate().equalsIgnoreCase(date)){
                        //添加
                        watchDateVo.getWatchTimesList().add(watchTimes);
                    }
                }
                /////////////////////////////////////////////////////
                filmVo.getWatchDateVoList().add(watchDateVo);
            }
            //////////////////////////////////////////////////////////////////////////////
            //遍历到的视图模型对象放入视图模型集合中
            filmVoList.add(filmVo);
        }
        return filmVoList;
    }


}
