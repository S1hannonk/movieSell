package movieSell.service.impl;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import movieSell.service.SMSService;
import movieSell.util.AliSMSClient;
import movieSell.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * projectName:
 *
 * @author: Shannon
 * description:短信实现类
 */
@Service
public class SMSServiceImpl implements SMSService {
    //依赖项
    @Resource
    private AliSMSClient aliSMSClient;
    @Resource
    private RedisUtil redisUtil;

    /**
     * @param phone
     * @return
     */
    @Override
    public boolean sendSMSValidate(String phone) {
        //获取随机6位数随机验证码[100000 - 999999]
        int code = (int) (Math.random() * (999999 - 100000 + 1) + 100000);

        // 创建 发送短信的请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 设置接收短信的手机号码
        request.setPhoneNumbers(phone);
        // 设置短信签名（企业申请）
        request.setSignName("学掌门超全栈开发");
        // 设置短信模板（企业申请）
        request.setTemplateCode("SMS_243437190");
        // 设置短信模板中的变量参数
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        try {
            // 发送短信请求 得到 短信响应
            SendSmsResponse response = aliSMSClient.creatClient().sendSmsWithOptions(request, new RuntimeOptions());
            // 判断 短信发送是否成功
            if ("OK".equalsIgnoreCase(response.getBody().getCode())) {
                // 短信发送成功...
                //业务成功
                ///////////////////////////////////////////////
                // 服务器端存储数据的方案
                // A:Request 请求作用域
                // B:Session 会话作用域
                // C：ServletContext 上下文作用域
                // D:文件系统
                // E:SQL 关系型数据库
                // F: NO-SQL非关系型数据库
                ///////////////////////////////////////////////
                //将正确的短信验证码code存入到Redis 中
                //key的颗粒度： 包含用户信息 + 业务信息
                String key = "SMS-Validate" + phone;
                redisUtil.set(key, code, 60 * 30);
                return true;
            } else {
                // 短信发送失败...
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
