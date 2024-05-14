package movieSell.controller;

import movieSell.bean.bo.UserLoginBo;
import movieSell.bean.bo.UserRegistBo;
import movieSell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @param userRegistBo 客户注册业务模型对象
     * @return 响应报文
     */
    @PostMapping("/regist")
    public Object regist(UserRegistBo userRegistBo) {
        Map<String, Object> responseBody = new HashMap<>();
        //调用业务逻辑层 完成注册业务逻辑功能
        //判断注册业务
        int regist = userService.regist(userRegistBo);
        if (regist == 1) {
            responseBody.put("code", 200);
            responseBody.put("message", "ok");
        } else if (regist == -1) {
            responseBody.put("code", 501);
            responseBody.put("message", "验证码校验失败");
        } else {
            responseBody.put("code", 502);
            responseBody.put("message", "改手机号已注册账户，请直接登录");
        }
        //返回响应报文
        return null;
    }

    /**
     * @param userLoginBo
     * @return 响应报文
     */
    @PostMapping("/login")
    public Object login(UserLoginBo userLoginBo){
        //实例化报文体对象
        Map<String,Object> responseBody = new HashMap<>();
        //调用业务层逻辑 完成客户登录验证
        String loginToken = userService.login(userLoginBo);
        if(loginToken == null){
            responseBody.put("code","401");
            responseBody.put("message","Unauthorized");
        }else {
            responseBody.put("code","200");
            responseBody.put("message","ok");
            responseBody.put("data",loginToken);
        }
        //返回 响应报文体
        return responseBody;
    }
}
