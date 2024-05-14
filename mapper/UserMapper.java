package movieSell.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import movieSell.bean.po.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * projectName:
 *
 * @author: Shannon
 * description:客户数据眼
 */
@Repository
public interface UserMapper extends BaseMapper<UserInfo> {
}
