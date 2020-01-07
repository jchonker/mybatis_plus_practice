package com.zhu.reverseEngineer.service.impl;

import com.zhu.reverseEngineer.entity.Employee;
import com.zhu.reverseEngineer.mapper.EmployeeMapper;
import com.zhu.reverseEngineer.service.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
