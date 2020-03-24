package com.leave996.leave996.lombok;

import lombok.EqualsAndHashCode;

/**
 * @EqualsAndHashCode注解
 * 生成EqualsAndHashCode()方法
 */
@EqualsAndHashCode(
        exclude = {"field1"}
)
public class EqualsAndHashCodeTest {

    private String field1;

    private String field2;
}
