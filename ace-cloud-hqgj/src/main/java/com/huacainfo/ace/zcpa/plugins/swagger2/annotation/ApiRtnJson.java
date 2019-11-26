package com.huacainfo.ace.zcpa.plugins.swagger2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ApiRtnJson
 * @Description 返回对象的定义 (描述这个类的作用)
 * @Author Arvin Zou
 * @Date 2019/5/15 18:29
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRtnJson {
    String key();  //对象名称

    ApiRtnJsonPro[] value(); //对象属性值
}
