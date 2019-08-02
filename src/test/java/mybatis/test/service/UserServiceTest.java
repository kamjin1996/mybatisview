package mybatis.test.service;

import mybatis.test.Application;
import mybatis.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void find() {
        User user = this.userService.findById(10);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void findByName() {
        User user = this.userService.findByName("李四");
        Assert.assertNotNull(user);
    }

    @Test
    public void insert() {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(16);
            user.setPassword("123456");
            user.setUsername("小明" + i);
            this.userService.add(user);
        }

    }

    @Test
    public void update() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(16);
            user.setPassword("123456");
            user.setUsername("小明" + i);
            this.userService.add(user);
        }

    }

    @Test
    public void delete(){
        User user = new User();
        user.setAge(16);
       this.userService.delete(user);
    }

}