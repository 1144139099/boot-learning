package com.hlh.orm.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.util.Date;

@Component
@Slf4j
public class DataBaseAuditListener {

    @PrePersist
    public void prePersist(Object object) throws IllegalArgumentException,IllegalAccessException{
        Class<?> aClass = object.getClass();
        try {
            addOperateTime(object, aClass, "createTime");
        }catch (NoSuchFieldException e){
            log.error("反射获取属性异常: ", e);
        }
    }

    @PreUpdate
    public void preUpdate(Object object) throws IllegalArgumentException, IllegalAccessException{
        Class<?> aClass = object.getClass();
        try {
            addOperateTime(object, aClass, "updateTime");
        }catch (NoSuchFieldException e){
            log.error("反射获取属性异常: ", e);
        }

    }

    protected void addOperateTime(Object object, Class<?> aClass, String propertyName) throws NoSuchFieldException, IllegalAccessException{
        Field time = aClass.getDeclaredField(propertyName);
        time.setAccessible(true);

        Object createdTimeValue = time.get(object);
        if (createdTimeValue == null){
            time.set(object, new Date());
        }
    }

    @PostPersist
    public void postPersist(Object object) throws IllegalArgumentException, IllegalAccessException{

    }

    @PostUpdate
    public void postUpdate(Object object) throws IllegalArgumentException, IllegalAccessException{

    }
}
