package movieSell.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * projectName:
 *
 * @author: Shannon
 * description:演示kafka消费者
 */
@Component
public class MQConsumer {
    /**
     *监听器回调方法
     * @param record 消息对象
     * @param ack 回执
     */
    @KafkaListener(topics = "test02",groupId = "group-01")
    public void test02(ConsumerRecord<String,String> record, Acknowledgment ack) throws InterruptedException {
        System.out.println("=>>>>>>>消费者 =>>>>>>接收到了消息 key=" + record.key() + "value=" + record.value());

        System.out.println("消费者 执行=>>>>>>> 任务开始");
        for (int i = 0; i < 10; i++) {
            System.out.println("消费者 任务进度：" + i + "/10");
            Thread.currentThread().sleep(1000);
        }
        System.out.println(" 消费者  ==>>>>>>>>> 任务完成");
        ack.acknowledge();//提交回执
    }
}
