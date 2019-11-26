package com.huacainfo.ace.common.log.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 被操作的对象
     */
    String operationObj() default "";


    /**
     * 操作
     */
    String operationType() default "";


    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";
}
