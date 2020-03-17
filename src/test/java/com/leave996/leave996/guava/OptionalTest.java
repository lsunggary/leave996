package com.leave996.leave996.guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional工具集使用
 */
public class OptionalTest {

    /**
     * 三种方式创建
     */
    @Test
    public void test () throws Throwable {
        //创建空的Optional对象
        Optional.empty();

        //使用非Null值创建Optional对象
        Optional.of("notnulloptionalobject");

        //使用任意值创建Optional对象
        Optional optional = Optional.ofNullable("notnulloptionalobject");

        //判断是否为空(建议不直接使用)
        optional.isPresent();

        // 当optional引用存在时执行
        // 类似的方法： map filter flatMap
        optional.ifPresent(System.out::println);

        /**
         * 当Optional引用缺失时
         */
        optional.orElse("引用缺失");
        optional.orElseGet(() -> {
            // 自定义引用缺失时返回值
            return "自定义引用缺失返回值";
        });

        optional.orElseThrow(() -> {
            throw new RuntimeException("引用缺失异常");
        });
    }

    public static void stream(List<String> list) {

        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
//        list.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        stream(null);
    }
}
