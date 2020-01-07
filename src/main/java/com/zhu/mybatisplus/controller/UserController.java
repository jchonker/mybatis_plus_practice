package com.zhu.mybatisplus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhu.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试AR操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserController {
    //新增操作
    @Test
    public void testArInsert(){
        User user = new User();
        user.setName("林青霞");
        user.setAge(22);
        user.setGender(1);
        boolean result = user.insert();   //类对象就可以直接调用baseMapper中的方法了
        System.out.println(result);
    }

    //更新操作
    //user调用updateById方法，将id为1的用户进行更新。
    @Test
    public void testArUpdate(){
        User user = new User();
        user.setId(1);
        user.setName("刘亦菲");
        boolean result = user.updateById();
        System.out.println(result);
    }

    //查询操作
    @Test
    public void testArSelect(){
        User user = new User();
//        //1:根据id查询
//        user = user.selectById(1);
//        //或者这样用
////        user.setId(1);
////        user = user.selectById();
//        System.out.println(user);

        //2:查询所有
//        List<User> userList = user.selectAll();
//        System.out.println(userList);

        //3:根据条件查询
//        List<User> users = user.selectList(new EntityWrapper<User>().like("name","刘"));
//        System.out.println(users);

        //4:查询符合条件的总数
        int result = user.selectCount(new EntityWrapper<User>().eq("gender",1));
        System.out.println(result);
    }


    //删除操作
    @Test
    public void testArDelete(){
        User user = new User();
//        //删除数据库中不存在的数据也是返回true
//        //1:根据id删除数据
//        boolean result = user.deleteById(1);
//        //或者这样写
//        user.setId(1);
//        boolean result = user.deleteById();
//        System.out.println(result);

        //2:根据条件删除
        boolean result = user.delete(new EntityWrapper<User>().like("name","玲"));
        System.out.println(result);
    }


    //AR分页操作：
    /*
        这个分页方法和BaseMapper提供的分页一样都是内存分页，并非物理分页，因为sql语句中没用limit，
        和BaseMapper的selectPage方法一样，配置了分页插件后就可以实现真正的物理分页。
        AR的分页方法与BaseMapper提供的分页方法不同的是，BaseMapper的selectPage方法返回值是查询到的
        记录的list集合，而AR的selectPage方法返回的是page对象，该page对象封装了查询到的信息，
        可以通过getRecords方法获取信息
     */
    @Test
    public void testArPage(){
        User user = new User();
        Page<User> page = user.selectPage(
                new Page<User>(1,4),
                new EntityWrapper<User>().like("name","刘"));
        List<User> users = page.getRecords();
        System.out.println(users);

    }
}
