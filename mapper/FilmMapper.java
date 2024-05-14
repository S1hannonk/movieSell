package movieSell.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import movieSell.bean.po.Film;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * @Param: 分类编号
 * description:
 */
@Repository
public interface FilmMapper extends BaseMapper<Film> {
    /**
     *根据分类编号 查询该分类的影片列表
     * @param typeId
     * @return 影片实体模型对象集合
     */
    List<Film> selectListByTypeId(@Param("typeId") Integer typeId);

    /**
     * 根据分类编号 查询该分类的影片总记录数
     * @param typeId
     * @return 总记录数
     */
    Integer selectCountByTypeID(@Param("typeId") Integer typeId);

    List<Film> selectListOrderBy(@Param("oderColumn") String orderColumn);
}
