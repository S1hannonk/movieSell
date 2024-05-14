package movieSell.service;

import movieSell.bean.po.Comment;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface CommentService {
    /**
     * 根据影片编号从es中查询该影片的影评信息
     * @param filmId
     * @return 影评实体模型对象的集合
     */
    List<Comment> searchListByFilmId(Integer filmId);
}
