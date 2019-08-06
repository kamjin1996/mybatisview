package mybatis.test.intercept.config;


import mybatis.test.intercept.exception.InterceptRuntimeException;

import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
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
            Field[] fields = ConfigInit.class.getFields();
            ConfigInit obj = ConfigInit.class.newInstance();
            URL resource = ClassLoader.getSystemClassLoader().getResource("");

            if (Objects.nonNull(resource)) {
                File pathFile = new File(resource.getFile());
                if (pathFile.isDirectory()) {
                    File[] files = pathFile.listFiles();
                    if (Objects.nonNull(files) && files.length > 0) {
                        Properties prop = new Properties();

                        for (File file : files) {
                            boolean isPropertiesFile = file.isFile() && file.getName().endsWith(".properties");
                            if (isPropertiesFile) {
                                prop.load(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(file.getName()),
                                        StandardCharsets.UTF_8));

                                for (Field field : fields) {
                                    String property = prop.getProperty(field.getName().replace("_", ".").toLowerCase());

                                    if (Objects.isNull(property) || Objects.equals(property, "")) {
                                        break;
                                    }

                                    Object value = property;
                                    if (field.getType().isAssignableFrom(Boolean.class)) {
                                        value = Boolean.valueOf(property);
                                    } else if (field.getType().isAssignableFrom(Integer.class)) {
                                        value = Integer.valueOf(property);
                                    }
                                    field.set(obj, value);
                                }
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InterceptRuntimeException("配置文件中，加密插件属性加载失败");
        }

    }
}
