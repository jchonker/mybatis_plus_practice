package com.zhu.mybatisplus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhu.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

//虽然AR模式用不到该接口，但是一定要定义，否则使用AR时会报空指针异常。
@Repository
public interface UserDao extends BaseMapper<User> {

}
