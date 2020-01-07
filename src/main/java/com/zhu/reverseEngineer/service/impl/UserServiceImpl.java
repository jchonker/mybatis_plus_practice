package com.zhu.reverseEngineer.service.impl;

import com.zhu.reverseEngineer.entity.User;
import com.zhu.reverseEngineer.mapper.UserMapper;
import com.zhu.reverseEngineer.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jchonker
 * @since 2020-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
