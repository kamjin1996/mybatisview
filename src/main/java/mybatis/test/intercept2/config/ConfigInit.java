package mybatis.test.intercept2.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @auther: tuosen
 * @date: 10:22 2019-08-01
 * @description: 配置参数载入
 */
@Component
@Data
public class ConfigInit {

    @Value("${DbCrypt.secretKey}")
    private String dbCryptSecretKey;

    @Value("${DbCrypt.enable}")
    private Boolean dbCryptEnable;
}
