package mybatis.test.intercept2.executor;

import mybatis.test.intercept2.exception.MyRuntimeException;
import mybatis.test.intercept2.util.CryptUtil;

/**
 * @auther: tuosen
 * @date: 14:13 2019-07-31
 * @description: 普通加密解密执行者
 */
public class CommonCryptExecutor implements CryptExecutor {

    @Override
    public String encrypt(String str) {
        String encrypt;
        try {
            encrypt = CryptUtil.Encrypt(str);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage());
        }
        return encrypt;
    }

    @Override
    public String decrypt(String str) {
        return CryptUtil.Decrypt(str);
    }
}
