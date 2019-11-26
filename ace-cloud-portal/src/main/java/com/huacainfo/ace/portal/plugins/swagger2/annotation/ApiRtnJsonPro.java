package com.huacainfo.ace.portal.plugins.swagger2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ApiRtnJsonPro
 * @Description 每一个字段的定义备注说明
 * @Author Arvin Zou
 * @Date 2019/5/15 18:30
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRtnJsonPro {
    String key();  //key

    String example() default "";

    Class<?> dataType() default String.class;

    String description() default "";
}
