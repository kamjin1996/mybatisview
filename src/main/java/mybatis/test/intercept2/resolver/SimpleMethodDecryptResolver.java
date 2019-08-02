package mybatis.test.intercept2.resolver;

import lombok.Getter;
import mybatis.test.intercept2.handler.CryptHandlerFactory;

/**
 * 简单解密处理者
 *
 * @author tuosen
 * @date 2019-08-01 13:12
 */
@Getter
public class SimpleMethodDecryptResolver implements MethodDecryptResolver {

    @Override
    public Object processDecrypt(Object param) {
        return CryptHandlerFactory.getCryptHandler(param, null).decrypt(param, null);
    }

}