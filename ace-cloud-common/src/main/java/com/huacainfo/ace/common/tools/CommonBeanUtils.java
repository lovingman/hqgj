package com.huacainfo.ace.common.tools;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class CommonBeanUtils extends BeanUtils {


    public static void copyProperties(Object dest, Object orig)
            throws InvocationTargetException, IllegalAccessException {
        BeanUtils.copyProperties(dest, orig);
    }


    /**
     * 将bean属性拷贝到map
     *
     * @param bean
     * @param map
     * @param containPrarent bean对象是否包含父类属性
     */
    public static void copyBean2Map(Object bean, Map<String, Object> map,
                                    boolean containPrarent) {
        if (containPrarent) {
            copyByMethod(bean, map);
        } else {
            copyByField(bean, map);
        }
    }

    /**
     * 将bean属性拷贝到map
     *
     * @param bean
     * @param map
     */
    public static void copyBean2Map(Object bean, Map<String, Object> map) {
        copyByField(bean, map);
    }

    /**
     * 通过set方法将bean属性拷贝到map
     *
     * @param bean
     * @param map
     */
    private static void copyByMethod(Object bean, Map<String, Object> map) {
        if (bean == null || map == null)
            return;
        PropertyDescriptor[] propertyDescriptors = PropertyUtils
                .getPropertyDescriptors(bean);
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String name = descriptor.getName();
            if ("class".equals(name))
                continue;
            try {
                Object value = descriptor.getReadMethod().invoke(bean,
                        new Object[0]);
                map.put(name, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 直接将bean属性拷贝到map
     *
     * @param bean
     * @param map
     */
    private static void copyByField(Object bean, Map<String, Object> map) {
        if (bean == null || map == null)
            return;
        Field[] beanFields = bean.getClass().getDeclaredFields();
        // 设置访问权限
        AccessibleObject.setAccessible(beanFields, true);
        for (Field field : beanFields) {
            String key = field.getName();
            Object value = null;
            try {
                value = field.get(bean);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        }
    }

    /**
     * 将map的内容拷贝到Java bean
     *
     * @param bean
     * @param map
     */
    public static void copyMap2Bean(Object bean, Map<String, Object> map) {
        if (bean == null || map == null)
            return;
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Filed数组
     *
     * @param <T>
     * @param t
     * @return Field[] 如果参数为<code>null</code>，则返回<code>null</code>。
     */
    public static <T> Field[] getFields(T t) {
        if (t == null)
            return null;
        Field[] fields = t.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        return fields;
    }

    /**
     * 获取<T>实例
     *
     * @param <T>
     * @param clazz
     * @return T 如果参数为<code>null</code>，则返回<code>null</code>。
     */
    public static <T> T getInstance(Class<T> clazz) {
        if (clazz == null)
            return null;
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 去除空格
     *
     * @param bean 需要处理的bean
     */
    public static void trim(Object bean) {
        PropertyDescriptor[] propertyDescriptors = PropertyUtils
                .getPropertyDescriptors(bean);
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String name = descriptor.getName();
            if ("class".equals(name))
                continue;
            try {
                Object value = descriptor.getReadMethod().invoke(bean,
                        new Object[0]);
                if (String.class.isInstance(value)) {
                    String newValue = ((String) value).trim();
                    CommonBeanUtils.setProperty(bean, name, newValue);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object bytes2Object(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);

            ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(bi));

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static byte[] object2Byte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
