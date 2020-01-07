package com.zhu.mybatisplus;

import com.zhu.mybatisplus.dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class Test {
    @Autowired
    private DataSource dataSource;

    @org.junit.Test
    public void testDataSource() throws SQLException {
        //获取是否连接成功
        System.out.println(dataSource.getConnection());
    }

    @Autowired
    UserDao userDao;

    //测试逻辑删除
    @org.junit.Test
    public void testLogicDelete(){
        /*
            配置逻辑删除后执行删除操作时的sql语句如下:
            UPDATE
                tb_user
            SET
                logic_flag=-1
            WHERE
                id=1]
         */
        Integer result = userDao.deleteById(1);
        System.out.println(result);
    }
}
