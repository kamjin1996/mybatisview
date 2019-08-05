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
            props.load(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties"),
                    "UTF-8"));
            String envFile = "application.properties";
            String activeProfile = props.getProperty("spring.profiles.active");
            if (StringUtils.isNotBlank(activeProfile)) {
                envFile = "application-" + activeProfile.toLowerCase() + ".properties";
            }
            Properties envProps = new Properties();
            envProps.load(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(envFile), "UTF-8"));
            Field[] fields = ConfigInit.class.getFields();
            ConfigInit configInit = ConfigInit.class.newInstance();
            for (Field field : fields) {
                String property = envProps.getProperty(field.getName().replace("_", ".").toLowerCase());
                Object value = property;
                if (field.getType().isAssignableFrom(Boolean.class)) {
                    value = Boolean.valueOf(property);
                } else if (field.getType().isAssignableFrom(Integer.class)) {
                    value = Integer.valueOf(property);
                }
                field.set(configInit, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
