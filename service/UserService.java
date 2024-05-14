package movieSell.service;

import movieSell.bean.bo.UserLoginBo;
import movieSell.bean.bo.UserRegistBo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:业务逻辑层接口
 */
public interface UserService {
    /**
     *
     * @param userRegistBo
     * @return 客户注册是否成功  （1：注册成功 -1 ： 验证码校验失效 -2： 手机号已经注册）
     */
    int regist(UserRegistBo userRegistBo);

    /**
     *
     * @param userLoginBo
     * @return 签发Token令牌 ，null 标识登陆验证失败
     */
    String login(UserLoginBo userLoginBo);
}
