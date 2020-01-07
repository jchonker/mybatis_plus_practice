package com.zhu.fieldAutoFill;

import com.zhu.mybatisplus.dao.UserDao;
import com.zhu.mybatisplus.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试时出现
 */
public class TestFieldAutoFill {

    @Autowired
    UserDao userDao;

    @Test
    public void testHandleInsert(){
        User user = new User();
        user.setGender(1);
        user.setAge(22);
        user.setLogicFlag(1);
        userDao.insert(user);
    }
}
