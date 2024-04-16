package spring.cloud.alicloud.oss;

import com.aliyun.oss.OSS;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 *
 * @author jaclon
 * @since 2024/3/18 09:28
 */
@SpringBootApplication
public class OSSApplication {
    public static final String BUCKET_NAME = "test-jason-oss";

    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class,args);
    }
    @Bean
    public AppRunner appRunner(){
        return new AppRunner();
    }

    class AppRunner implements ApplicationRunner {
        @Resource
        private OSS ossClient;
        @Override
        public void run(ApplicationArguments args) throws Exception {
            try {
                if (!ossClient.doesBucketExist(BUCKET_NAME)) {
                    ossClient.createBucket(BUCKET_NAME);
                }
            } catch (Exception e) {
                System.err.println("oss handle bucket error: " + e.getMessage());
                System.exit(-1);
            }
        }
    }
}
