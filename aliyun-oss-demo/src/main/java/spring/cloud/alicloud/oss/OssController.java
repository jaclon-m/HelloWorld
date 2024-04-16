package spring.cloud.alicloud.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;


/**
 *
 * @author jaclon
 * @since 2024/3/18 09:36
 */
@RestController
public class OssController {
    @Autowired
    private OSS ossClient;
    @Value("OSS://test-jason-oss/oss-test.json")
    private Resource file;

    @GetMapping("/upload")
    public String upload(){
        try {
            ossClient.putObject("test-jason-oss", "oss-test.json", this
                    .getClass().getClassLoader().getResourceAsStream("oss-test.json"));
        } catch (Exception e) {
            e.printStackTrace();
            return "upload fail: " + e.getMessage();
        }
        return "upload success";
    }
    @GetMapping("/file-resource")
    public String fileResource() {
        try {
            return "get file resource success. content: " + StreamUtils.copyToString(
                    file.getInputStream(), Charset.forName(CharEncoding.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return "get resource fail: " + e.getMessage();
        }
    }
    @GetMapping("/download")
    public String download() {
        try {
            OSSObject ossObject = ossClient.getObject("test-jason-oss", "oss-test.json");
            return "download success, content: " + IOUtils
                    .readStreamAsString(ossObject.getObjectContent(), CharEncoding.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "download fail: " + e.getMessage();
        }
    }

}
