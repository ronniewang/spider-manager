import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.spider.utils.MessageSender;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2016/3/29-14:28.
 *
 * @author wsy
 */
public class TestOnsMq {

//    public static void main(String[] args) throws IOException, InterruptedException {
//
//
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        List<Header> headers = new ArrayList<>();
//        Header userAgentHeader = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
//        Header acceptHeader = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        headers.add(userAgentHeader);
//        headers.add(acceptHeader);
//        httpClientBuilder.setDefaultHeaders(headers);
//        HttpClient httpClient = httpClientBuilder.build();
//        HttpGet request = new HttpGet("http://localhost:8081/spider-web/testPerformance");
//        for (int i = 0; i < 100; i++) {
//            httpClient.execute(request);
//        }
//        TimeUnit.SECONDS.sleep(5);
//    }

}
