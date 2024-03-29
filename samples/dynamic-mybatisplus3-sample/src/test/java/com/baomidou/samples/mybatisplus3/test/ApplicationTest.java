package com.baomidou.samples.mybatisplus3.test;

import com.baomidou.samples.mybatisplus.Application;
import com.baomidou.samples.mybatisplus.entity.User;
import com.baomidou.samples.mybatisplus.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

  private Random random = new Random();

  @Autowired
  private DataSource dataSource;
  @Autowired
  private UserService userService;

  @Before
  public void beforeTest() {
    try {
      Connection connection = dataSource.getConnection();
      connection.createStatement().execute("CREATE TABLE IF NOT EXISTS  USER (\n" +
          "  id BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
          "  name VARCHAR(30) NULL DEFAULT NULL ,\n" +
          "  age INT(11) NULL DEFAULT NULL ,\n" +
          "  PRIMARY KEY (id)\n" +
          ");");
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddUser() {
    User user = new User();
    user.setName("测试用户" + random.nextInt());
    user.setAge(random.nextInt(100));
    userService.addUser(user);
  }

  @Test
  public void testSelectUser() {
    userService.list(null);
  }
}
