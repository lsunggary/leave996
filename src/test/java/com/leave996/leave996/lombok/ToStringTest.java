package com.leave996.leave996.lombok;

import lombok.ToString;

/**
 * @ToString 注解
 * 生成toString()方法
 */
@ToString(
        includeFieldNames = false,
        /**
         * exclude与of互斥。
        */
        exclude = {"password"},
        of = {"name"},
        /**
         * 如果doNotUserGetters为true，则不调用getter方法，直接读取属性的值
         * 如果为false，则会调用getter方法
         * 例如下面的getName。如果不调用则不打印 “111”
        */
        doNotUseGetters = false
)
public class ToStringTest {

    private String password;

    private String name;

    public String getName() {
        System.out.println("111");
        return this.name;
    }
}
