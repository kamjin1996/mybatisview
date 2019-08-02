package mybatis.test.intercept2.handler;


import mybatis.test.intercept2.annotation.CryptField;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * * 处理 Collection 对象的加解密
 *
 * @author tuosen
 * @date 2019-07-30 11:40
 */
public class CollectionCryptHandler implements CryptHandler<Collection> {


    @Override
    public Object encrypt(Collection collection, CryptField cryptField) {
        if (!needCrypt(collection)) {
            return collection;
        }
        return collection.stream()
            .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).encrypt(item, cryptField))
            .collect(Collectors.toList());
    }

    @Override
    public Object decrypt(Collection param, CryptField cryptField) {
        if (!needCrypt(param)) {
            return param;
        }
        return param.stream()
            .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).decrypt(item, cryptField))
            .collect(Collectors.toList());
    }


    private boolean needCrypt(Collection collection) {
        return collection != null && collection.size() != 0;
    }
}
