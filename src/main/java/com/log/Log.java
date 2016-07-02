package com.log;

/**
 * Created by Administrator on 2016/6/26.
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String desc() default "没有标准描述";

    boolean view() default true;

    String operationDesc() default "没有默认描述";

}
