package mybatis.test.service;

import mybatis.test.model.User;

/**
 * @auther: tuosen
 * @date: 15:38 2019-07-31
 * @description: 用户服务
 */
public interface UserService {

    User findById(Integer id);

    void add(User user);

    void delete(User user);

    /**
     *
     * @return
     */
    User findByName(String username);
}
