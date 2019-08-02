package mybatis.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.test.intercept2.annotation.CryptField;
import mybatis.test.intercept2.executor.CryptType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    @CryptField(value = CryptType.SPECIAL, decrypt = false)
    private String username;

    private String password;

    private Integer age;

}