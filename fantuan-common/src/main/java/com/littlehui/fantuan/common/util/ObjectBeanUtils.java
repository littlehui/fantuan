package com.littlehui.fantuan.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by littlehui on 2016/10/5 0005.
 */
public class ObjectBeanUtils {

    public static <T> T setProperty(T t, String propertyName, Object property) {
        try {
            PropertyDescriptor propertyDescriptor = getProperty(Introspector.getBeanInfo(t.getClass()), propertyName);
            Method method = propertyDescriptor.getWriteMethod();
            method.invoke(t, property);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static PropertyDescriptor getProperty(BeanInfo beanInfo, String property) {
        PropertyDescriptor[] propertys = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertys) {
            if (propertyDescriptor.getName().equals(property) && !"class".equals(property)) {
                return propertyDescriptor;
            }
        }
        return null;
    }

    public static <T> Object getPropertyValue(T t, String propertyName) {
        try {
            PropertyDescriptor propertyDescriptor = getProperty(Introspector.getBeanInfo(t.getClass()), propertyName);
            Method method = propertyDescriptor.getReadMethod();
            return method.invoke(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
