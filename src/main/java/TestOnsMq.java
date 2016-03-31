import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.spider.utils.MessageSender;

import java.util.Properties;

/**
 * Created on 2016/3/29-14:28.
 *
 * @author wsy
 */
public class TestOnsMq {

    public static void main(String[] args) {

        String produceId = "PID-inplayPro";
        String accessKey = "KmLn7VAKs8S9mYCa";
        String secretKey = "aWT2OrNnO0q3UGHBHonmkeItM3mHFd";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, produceId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        Producer producer = ONSFactory.createProducer(properties);
        producer.start();
        System.out.println(producer.send(new Message("inplayParameter", "scoreAndHalf", new String("hah").getBytes())));
        producer.shutdown();
    }
}
