package movieSell.controller;

import movieSell.service.SMSService;
import movieSell.service.impl.SMSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * projectName:
 *
 * @author: Shannon
 * description: 短信模块通信 控制器
 */
@RestController
@RequestMapping("/SMS")
public class SMSController {

    @Autowired
    private SMSService smsService;
    /**
     *
     * @param phone
     * @return 响应报文 {"code" : 业务编码  ,错误代码  , "message" : 业务消息  ，”data“ : 业务数据}
     */
    @GetMapping("/Validate/{phone}")
    public Object sendSMSValidate(@PathVariable String phone){

        //实例化 响应报文
        HashMap<String, Object> responseBody = new HashMap<>();
        boolean result = smsService.sendSMSValidate(phone);
        //调用业务逻辑 完成短信发送的业务功能
        if(result){
            responseBody.put("code","200");
            responseBody.put("message","ok");

        }else {
            //业务失败
            responseBody.put("code","500");
            responseBody.put("message","false");
        }
        return responseBody;
    }
}
