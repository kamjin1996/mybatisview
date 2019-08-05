package mybatis.test.intercept.resolver;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mybatis.test.intercept.handler.CryptHandlerFactory;

/**
 * 简单加密处理者
 *
 * @author tuosen
 * @date 2019-08-01 13:12
 */
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMethodEncryptResolver implements MethodEncryptResolver {

    private MethodAnnotationEncryptParameter encryptParameter;

    @Override
    public Object processEncrypt(Object param) {
        return CryptHandlerFactory.getCryptHandler(param, encryptParameter.getCryptField()).encrypt(param, encryptParameter.getCryptField());
    }
}