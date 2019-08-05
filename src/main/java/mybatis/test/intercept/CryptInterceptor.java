package mybatis.test.intercept;


import mybatis.test.intercept.resolver.MethodCryptMetadata;
import mybatis.test.intercept.resolver.MethodCryptMetadataBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加解密插件
 *
 * @author tuosen
 * @date 2019-07-30 12:49
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class CryptInterceptor implements Interceptor {

    /**
     * 需加解密处理方法的信息
     */
    private static final ConcurrentHashMap<String, MethodCryptMetadata> METHOD_ENCRYPT_MAP = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MethodCryptMetadata methodCryptMetadata = getCachedMethodCryptMetaData((MappedStatement) args[0]);
        // 加密
        args[1] = methodCryptMetadata.encrypt(args[1]);
        System.out.println(args[1]);
        // 获得出参
        Object returnValue = invocation.proceed();
        // 解密
        return methodCryptMetadata.decrypt(returnValue);
    }

    private MethodCryptMetadata getCachedMethodCryptMetaData(MappedStatement mappedStatement) {
        return METHOD_ENCRYPT_MAP.computeIfAbsent(mappedStatement.getId(), id ->
                new MethodCryptMetadataBuilder(id).build()
        );
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
