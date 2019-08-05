package mybatis.test.intercept.config;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @auther: tuosen
 * @date: 10:22 2019-08-01
 * @description: 不依赖spring, 配置参数载入
 */
public class ConfigInit {

    public static String DbCrypt_SecretKey;
    public static Boolean DbCrypt_Enable;

    static {
        try {
            Properties props = new Properties();
            props.load(new InputStreamReader(
                    ConfigInit.class.getClassLoader().getResourceAsStream("application.properties"),
                    "UTF-8"));
            String envFile = "application.properties";
            String activeProfile = props.getProperty("spring.profiles.active");
            if (StringUtils.isNotBlank(activeProfile)) {
                envFile = "application-" + activeProfile + ".properties";
            }
            Properties envProps = new Properties();
            envProps.load(new InputStreamReader(
                    ConfigInit.class.getClassLoader().getResourceAsStream(envFile), "UTF-8"));
            Field[] fields = ConfigInit.class.getFields();
            for (Field field : fields) {
                field.set(null, envProps.getProperty(field.getName().replace("_", ".").toLowerCase()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
