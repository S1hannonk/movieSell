package movieSell.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import movieSell.bean.bo.UserLoginBo;
import movieSell.bean.bo.UserRegistBo;
import movieSell.bean.po.UserInfo;
import movieSell.mapper.UserMapper;
import movieSell.service.UserService;
import movieSell.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * projectName:
 *
 * @author: Shannon
 * description: 业务逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    /**
     * @param userRegistBo
     * @return 客户注册是否成功  （1：注册成功 -1 ： 验证码校验失效 -2： 手机号已经注册）
     */
    @Override
    public int regist(UserRegistBo userRegistBo) {
        //1.1获取用户填写的短信验证码
        String userSMSValidate = userRegistBo.getUserSMSValidate();
        //1.2 获取发送给注册用户的正确验证码
        String key = "SMS-Validate" + userRegistBo.getUserLoginName();
        //1.3 判断是否一致
        if (!redisUtil.hasKey(key) || redisUtil.get(key).equals(userSMSValidate)) {
            //验证码校验失败
            return -1;
        }
        //2.1手机号唯一性校验
        LambdaQueryWrapper<UserInfo> userInfoWrapper = Wrappers.lambdaQuery();
        userInfoWrapper.eq(UserInfo::getUserLoginName, userRegistBo.getUserLoginName());
        List<UserInfo> userInfoList = userMapper.selectList(userInfoWrapper);
        if (userInfoList.size() > 0) {
            return -2;
        }
        //3.开始注册业务
        //3.1 实例化 客户实体对象
        UserInfo userInfo = new UserInfo();
        //3.2设置 客户的账户名称（手机号）
        userInfo.setUserLoginName(userRegistBo.getUserLoginName());
        //3.3设置密码
        //生成密码盐值
        String salt = UUID.randomUUID().toString();
        //3.4对用户填写的密码明文进行加盐加密 生成密码密文
        userInfo.setUserLoginPass(
                //md5加密                  （               用户填写的密码明文    盐值     字节数组）
                DigestUtils.md5DigestAsHex((userRegistBo.getUserLoginPass() + salt).getBytes()));
        //3.5设置用户昵称
        userInfo.setUserNickName(userRegistBo.getUserNickName());
        //3.6设置客户的密码盐值
        userInfo.setUserPhone(salt);
        //3.7 设置用户的状态
        userInfo.setUserEnable((byte) 1);
        //3.8调用 数据访问层 将 客户实体对象模型添加到数据库
        userMapper.insert(userInfo);
        return 1;
    }

    @Override
    public String login(UserLoginBo userLoginBo) {
        //步骤一：账户名称校验  密码明文不能直接与密文进行比对
        LambdaQueryWrapper<UserInfo> userInfoWrapper = Wrappers.lambdaQuery();
        userInfoWrapper.eq(UserInfo::getUserLoginName, userLoginBo.getUserLoginName());
        List<UserInfo> userInfoList = userMapper.selectList(userInfoWrapper);
        if (userInfoList.size() == 0) {
            //账户名称校验失败
            return null;
        }

        //步骤二：账户密码校验
        //2.1 将用户填写的账户密码明文加盐加密
        String password =
                DigestUtils.md5DigestAsHex(
                        (userLoginBo.getUserLoginPass() + userInfoList.get(0).getUserPhone()).getBytes());
        //2.2判断用户填写密码明文加密后与存储的加密的密文是否相等
        if(!password.equals(userInfoList.get(0).getUserLoginPass())){
            //校验失败
            return null;
        }
        //步骤三 3.1：登录验证成功= > 为用户签发令牌并再redis中存储
        String token = JWT.create()            //创建Token令牌
                .withAudience(userLoginBo.getUserLoginName())                          //载荷用户信息
                .withIssuedAt(new Date())                                              //载荷签发时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 1000))  //载荷到期时间
                .sign(Algorithm.HMAC256(userInfoList.get(0).getUserLoginPass()));      //签发令牌

        //3.2 将签发的令牌存入redis中
        redisUtil.set("Bearer" + token,
                userInfoList.get(0),
                24*60);
        //3.3 将签发令牌返回
        return token;
    }
}
