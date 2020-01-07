package com.zhu.reverseEngineer.mapper;

import com.zhu.reverseEngineer.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jchonker
 * @since 2020-01-06
 */
@Repository   //此注解不会自动生成
public interface UserMapper extends BaseMapper<User> {
    //在mapper接口中自定义方法
    int deleteAll();
}
