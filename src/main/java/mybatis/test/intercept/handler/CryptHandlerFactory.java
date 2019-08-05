package mybatis.test.intercept.handler;


import mybatis.test.intercept.util.CryptUtil;
import mybatis.test.intercept.annotation.CryptField;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 加解密处理者工厂类
 *
 * @author tuosen
 * @date 2019-07-31 13:02
 */
public class CryptHandlerFactory {

    private static final CryptHandler STRING_HANDLER = new StringCryptHandler();
    private static final CryptHandler COLLECTION_HANDLER = new CollectionCryptHandler();
    private static final CryptHandler LIST_HANDLER = new ListCryptHandler();
    private static final CryptHandler ARRAY_HANDLER = new ArrayCryptHandler();
    private static final CryptHandler BEAN_HANDLER = new BeanCryptHandler();
    private static final CryptHandler EMPTY_HANDLER = new EmptyCryptHandler();


    public static CryptHandler getCryptHandler(Object obj, CryptField cryptField) {
        // 如果是map不处理
        if (obj == null || CryptUtil.inIgnoreClass(obj.getClass()) || obj instanceof Map) {
            return EMPTY_HANDLER;
        }

        if (obj instanceof String && cryptField == null) {
            return EMPTY_HANDLER;
        }
        if (obj instanceof String) {
            return STRING_HANDLER;
        }

        if (obj instanceof List) {
            return LIST_HANDLER;
        }

        if (obj instanceof Collection) {
            return COLLECTION_HANDLER;
        }

        if (obj.getClass().isArray()) {
            return ARRAY_HANDLER;
        }
        return BEAN_HANDLER;
    }

}
