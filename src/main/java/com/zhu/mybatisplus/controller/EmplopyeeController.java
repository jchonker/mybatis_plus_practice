package com.zhu.mybatisplus.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhu.mybatisplus.dao.EmplopyeeDao;
import com.zhu.mybatisplus.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class EmplopyeeController {
    @Autowired
    private EmplopyeeDao emplopyeeDao;

    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setLastName("东方不败");
        employee.setEmail("dfbb@163.com");
        employee.setGender(1);
        employee.setAge(20);
        emplopyeeDao.insert(employee);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println("插入成功:"+employee.getId());
    }

    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("更新测试1");
        emplopyeeDao.updateById(employee);  //根据id进行更新,没有传值的属性就不会更新
        //emplopyeeDao.updateAllColumnById(employee);  //根据id进行更新,没传值的属性就更新为null
        System.out.println("更新成功!");
    }

    //根据id进行查询
    @Test
    public void selectById(){
        Employee employee = emplopyeeDao.selectById(1);
        System.out.println(employee);
    }

    //根据条件查询一条数据
    @Test
    public void selectOneByCondition(){
        Employee employeeCondition = new Employee();
        employeeCondition.setId(1);
        employeeCondition.setLastName("更新测试");
        //若是数据库中符合传入的条件的记录有多条，只会查询出第一条数据，其他不会查询出(测试不会报错)
        Employee employee = emplopyeeDao.selectOne(employeeCondition);
        System.out.println(employee);
    }

    //根据查询条件返回多条数据
    @Test
    public void selectManyByCondition(){
        //使用map存放条件
        Map<String,Object> columnMap = new HashMap<String,Object>();
        columnMap.put("last_name","更新测试");  //写表中的列名
        columnMap.put("gender","1");
        List<Employee> employees = emplopyeeDao.selectByMap(columnMap);
        System.out.println("查询结果数量:"+employees.size());
        System.out.println("查询结果:"+employees);
    }

    //通过多个id值批量查询
    @Test
    public void selectManyByBatchID(){
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        List<Employee> employees = emplopyeeDao.selectBatchIds(idList);
        System.out.println("查询结果："+employees);
    }

    //查询所有数据
    @Test
    public void selectAll(){
        List<Employee> employees = emplopyeeDao.selectList(null);  //条件传入null就可以查询全部数据
        System.out.println(employees);
    }

    //分页查询(无查询条件)
    /**
    * 在page中传入分页信息，后者为null的分页条件，这里先让其为null.
    * 讲了条件构造器再说其用法。这个分页其实并不是物理分页，而是内存分页。
    * 也就是说，查询的时候并没有limit语句。等配置了分页插件后才可以实现真正的分页
    */
    /**
     * 物理分页与（逻辑分页就是内存分页）
     * 1.物理分页
     * 物理分页依赖的是某一物理实体，这个物理实体就是数据库，比如MySQL数据库提供了limit关键字，
     * 程序员只需要编写带有limit关键字的SQL语句，数据库返回的就是分页结果。
     * 2.逻辑分页
     * 逻辑分页依赖的是程序员编写的代码。数据库返回的不是分页结果，而是全部数据，
     * 然后再由程序员通过代码获取分页数据，常用的操作是一次性从数据库中查询出全部数据并存储到List集合中，
     * 因为List集合有序，再根据索引获取指定范围的数据。
     */
    @Test
    public void selectByPaging(){
        List<Employee> employees = emplopyeeDao.selectPage(new Page<Employee>(1,2),null);
        //查看sql语句：SELECT id AS id,last_name AS lastName,email,gender,age FROM tb_employee
        //可以发现并没有limit语句
        System.out.println(employees);
    }


    //根据id进行删除
    @Test
    public void deleteById(){
        emplopyeeDao.deleteById(1);
        System.out.println("删除成功!");
    }

    //根据条件删除
    @Test
    public void deleteByCondition(){
        Map<String,Object> columnMap = new HashMap<String,Object>();
        columnMap.put("gender",0);
        columnMap.put("age",18);
        emplopyeeDao.deleteByMap(columnMap);
    }


    //根据id批量删除
    @Test
    public void deleteByBatchId(){
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);
        emplopyeeDao.deleteBatchIds(idList);
    }


    //条件构造器(EntityWrapper)

    /**
     * 需要分页查询 tb_employee 表中，年龄在 18~50 之间性别为男且姓名为 xx 的所有用户
     */
    public void selectPagingByWrapper1(){
        List<Employee> employees = emplopyeeDao.selectPage(new Page<Employee>(1,3),
                new EntityWrapper<Employee>()
                        .between("age",18,50)
                        .eq("gender",0)
                        .eq("last_name","tom")
        );
        System.out.println(employees);
    }

    /**
     * 查询gender为0且名字中带有老师、或者邮箱中带有a的用户
     */
    public void selectPagingByWrapper2(){
        List<Employee> employees = emplopyeeDao.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender",0)
                        .like("last_name","老师")
                        //.or()//和or new 区别不大
                        .orNew()
                        .like("email","a")
        );
        System.out.println(employees);
    }


    /**
     * 查询gender为0，根据age排序，简单分页
     */
    @Test
    public void selectPagingByWrapper3(){
        List<Employee> employees = emplopyeeDao.selectList(
                new EntityWrapper<Employee>()
                .eq("gender",0)
                .orderBy("age")  //直接orderby是升序，asc
                .last("desc limit 1,3")  //在sql语句后面追加last里面的内容(改为降序,同时分页)
        );
        System.out.println(employees);
    }


    /**
     * 分页查询年龄在18 - 50且gender为0、姓名为tom的用户
     */
    @Test
    public void selectPagingByWrapper4(){
        List<Employee> employees = emplopyeeDao.selectPage(
                new Page<Employee>(1,2),
                Condition.create()
                    .between("age",18,50)
                    .eq("gender",0)
        );
        System.out.println(employees);
    }


    //根据条件更新
    /**
     * 把last_name为tom，age为25的所有用户的信息更新为employee中设置的信息
     */
    @Test
    public void updateByEntityWrapper(){
        //定义要更新的内容
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cjk@sina.com");
        employee.setGender(0);
        emplopyeeDao.update(
                employee,
                new EntityWrapper<Employee>()
                .eq("last_name","tom")
                .eq("age",25)
        );
    }


    //根据条件删除
    /**
     * 把last_name为tom、age为16的所有用户删除
     */
    @Test
    public void deleteByEntityWrapper(){
        emplopyeeDao.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name","tom")
                        .eq("age",16)
        );
    }


    //测试配置了分页插件后的物理分页
    @Test
    public void testPage(){
        //配置了分页插件后,还是和以前一样的使用selectpage方法
        //但是现在就是真正的物理分页了,sql语句中就有limit了
        Page<Employee> page = new Page<Employee>(1,2);
        List<Employee> employeeList = emplopyeeDao.selectPage(page,null);
        System.out.println(employeeList);
        System.out.println("===========相关的分页信息===========");
        System.out.println("总条数:"+page.getTotal());
        System.out.println("当前页码："+page.getCurrent());
        System.out.println("总页数:"+page.getPages());
        System.out.println("每页显示条数:"+page.getSize());
        System.out.println("是否有上一页:"+page.hasPrevious());
        System.out.println("是否有下一页:"+page.hasNext());
        //还可以将查询到的结果set进page对象中
        page.setRecords(employeeList);
    }


    //测试全表删除或者更新时错误(插件类SqlExplainInterceptor)
    @Test
    public void testSqlExplain(){
        //条件为null，就是删除全表,执行分析插件就会终止该操作
        emplopyeeDao.delete(null);
    }
}
