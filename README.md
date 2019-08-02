# mybatisview
我的mybatis脱敏插件,
插件实现了数据库的脱敏操作,
在cryptUtil中配置加解密算法,
使用方法:
注解在需要脱敏的实体类字段上,支持注解mapper中方法的参数
将插件在mybatis的xml配置文件中引入,或者用javaConfig的形式进行配置
配置文件支持配置加密秘钥,是否开启脱敏
