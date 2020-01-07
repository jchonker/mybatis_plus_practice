package com.zhu.reverseEngineer.mapper;

import com.zhu.reverseEngineer.entity.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jchonker
 * @since 2020-01-06
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    //在mapper接口中自定义方法
    int deleteAll();
}
