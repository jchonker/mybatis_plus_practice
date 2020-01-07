package com.zhu.mybatisplus.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

//测试ActiveRecord
//仅仅需要让实体类继承 Model 类且实现主键指定方法，即可开启 AR 之旅。使用此类对象直接调用方法就可以进行增删查改
@Data
public class User extends Model<User> {
    private Integer id;
    @TableField(fill = FieldFill.INSERT_UPDATE)  //插入和更新时填充(测试公共字段自动填充)
    private String name;
    private Integer age;
    private Integer gender;
    @TableLogic  //新增逻辑删除属性
    private Integer logicFlag;

    //继承Model<T>后重写此方法
    //return当前类的主键
    protected Serializable pkVal() {
        return id;
    }
}
