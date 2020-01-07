package com.zhu.reverseEngineer.testAutoSqlInjector;

import com.zhu.mybatisplus.dao.UserDao;
import com.zhu.reverseEngineer.mapper.EmployeeMapper;
import com.zhu.reverseEngineer.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试时出现空指针异常
 */
public class TestAutoSqlInjector {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDao userDao;

    @Test
    public void testMysqlInjector(){
        Integer result = employeeMapper.deleteAll();
        System.out.println(result);
    }

    @Test
    public void testMySqlInjector2(){
        Integer result = userMapper.deleteAll();
        System.out.println(result);
    }

}
