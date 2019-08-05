package mybatis.test.intercept.resolver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mybatis.test.intercept.annotation.CryptField;

/**
 * 方法加密注解了的参数
 *
 * @author tuosen
 * @date 2019-08-01 11:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
class MethodAnnotationEncryptParameter {

    private String paramName;
    private CryptField cryptField;
    private Class cls;
}
