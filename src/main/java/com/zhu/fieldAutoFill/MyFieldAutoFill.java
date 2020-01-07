package com.zhu.fieldAutoFill;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 公共字段填充处理器类
 * 类继承了MetaObjectHandler类，重写了insertFill和updateFill方法，
 * 在这两个方法获取需要填充的字段以及默认填充的值
 */
public class MyFieldAutoFill extends MetaObjectHandler {
    //插入时
    public void insertFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("name",metaObject); //获取需要填充的字段
        if(fieldValue == null){
            setFieldValByName("name","林志玲",metaObject); //那就将其设置为"林志玲"
        }
    }
    //更新时
    public void updateFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("name",metaObject); //获取需要填充的字段
        if(fieldValue == null){  //如果该字段没有设置值
            setFieldValByName("name","朱茵",metaObject);//那就将其设置为"朱茵"
        }
    }
}
