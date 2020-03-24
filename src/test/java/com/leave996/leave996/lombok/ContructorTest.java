package com.leave996.leave996.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @AllArgsConstuctor
 * @NoArgsConstuctor
 * @RequiredArgsConstuctor 部分字段不能为空的，设置一个构造方法
 */
@RequiredArgsConstructor
public class ContructorTest {

    private final String field1;

    @NonNull
    private String field2;

    private String field3;

    private Integer status = 0;

}
