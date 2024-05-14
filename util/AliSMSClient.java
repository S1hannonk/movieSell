package movieSell.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import jdk.jfr.DataAmount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */

/**
 *
 * @return Client
 * @throw Exception
 */
@Configuration
public class AliSMSClient {
    @Bean
    public Client creatClient() throws Exception {
        Config config = new Config()
                //AccessID
                .setAccessKeyId("你自己注册的认证ID")
                //Access 密钥
                .setAccessKeySecret("认证密钥");
        //访问域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        return client;
    }
}
