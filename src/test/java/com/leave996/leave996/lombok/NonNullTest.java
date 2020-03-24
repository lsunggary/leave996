package com.leave996.leave996.lombok;

import lombok.NonNull;

/**
 * @NonNull 注解
 * 生成一个非空检查
 */
public class NonNullTest {

    public NonNullTest(@NonNull String field) {
        System.out.println(field);
    }
}
