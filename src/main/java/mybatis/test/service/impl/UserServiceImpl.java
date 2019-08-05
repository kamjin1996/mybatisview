package mybatis.test.service.impl;

import mybatis.test.mapper.UserMapper;
import mybatis.test.model.User;
import mybatis.test.model.UserExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mybatis.test.service.UserService;

import java.util.List;

/**
 * @auther: tuosen
 * @date: 15:39 2019-07-31
 * @description: 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(User user) {
        this.userMapper.insert(user);
    }

    @Override
    public void delete(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAgeEqualTo(user.getAge());
        this.userMapper.deleteByExample(example);
    }

    @Override
    public User findByName(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        //select * from t_user where username = "a";
        List<User> userList = this.userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }
}
