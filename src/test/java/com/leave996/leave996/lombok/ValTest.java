package com.leave996.leave996.lombok;

import lombok.val;

import java.util.ArrayList;

/**
 * @Val注解
 * 相当于弱语言中的变量声明，可以接受任何变量参数
 * 编译后生成的class文件为对应的类型。
 */
public class ValTest {

    public ValTest() {
        val field = "11111cfds";

        val list = new ArrayList<String>();
        list.add(field);

    }
}
