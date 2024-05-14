package movieSell.controller;

import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@RestController
@RequestMapping("/mq")
public class MQController {

    @GetMapping("/synchronize")//同步任务
    public Object synchronize() throws InterruptedException {

        System.out.println("=>>>>>>> 任务开始");
        for (int i = 0; i < 10; i++) {
            System.out.println("任务进度：" + i + "/10");
            Thread.currentThread().sleep(1000);
        }
        System.out.println("==>>>>>>>>> 任务完成");
        Map<String,Object> responseBody = new HashMap<>();
        //设置响应报文体 业务编码
        responseBody.put("code","200");
        //响应报文体 业务信息
        responseBody.put("massage","ok");


        //返回
        return responseBody;
    }

    //依赖项
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/asynchronize")
    public Object asynchronize(){//异步任务
        //发送消息到kafka 中的test02 话题
        kafkaTemplate.send("test02","dashabi","???????");

        Map<String,Object> responseBody = new HashMap<>();
        //设置响应报文体 业务编码
        responseBody.put("code","200");
        //响应报文体 业务信息
        responseBody.put("massage" ,"ok");



        //返回
        return responseBody;
    }
}
