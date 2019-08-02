package mybatis.test.intercept2.annotation;


import mybatis.test.intercept2.executor.CryptType;

import java.lang.annotation.*;

/**
 * 加解密注解
 *
 * @author tuosen
 * @date 2019-08-01 14:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface CryptField {

    CryptType value() default CryptType.COMMON;

    boolean encrypt() default true;

    boolean decrypt() default true;
}

