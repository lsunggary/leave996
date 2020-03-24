package com.leave996.leave996.stream;


import com.alibaba.fastjson.JSON;
import com.leave996.leave996.cart.CartService;
import com.leave996.leave996.cart.Sku;
import com.leave996.leave996.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class StreamOperator {

    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * filter stream
     */
    @Test
    public void filterTest() {
        list.stream()
                .filter(sku ->
                        SkuCategoryEnum.BOOKS
                                .equals(sku.getSkuCategory()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    /**
     * map使用，讲一个元素转换成另一个元素
     */
    @Test
    public void mapTest() {
        list.stream()
                .map(Sku::getSkuName)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    /**
     * flatMap使用：讲一个对象转换成流
     */
    @Test
    public void flatMapTest() {
        list.stream()
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void peekTest() {
        list.stream()
                //遍历， 中间操作，使用后还可以使用该流，foreach后不可使用
                //流：无状态的中间操作和终端操作交替执行
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void sortTest() {
        list.stream()
                // 无状态操作 做完即往下执行
                .peek(sku -> System.out.println(sku.getSkuName()))
                // 有状态操作 要执行所有数据，对数据进行汇总再往下执行
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void distinctTest() {
        list.stream()
                .map(Sku::getSkuCategory)
                .distinct()
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void skipTest () {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(2)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .limit(3)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)
                        ));
    }

    @Test
    public void allMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // 终端操作 短路操作 只要不满足即返回返回值
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    @Test
    public void anyMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    @Test
    public void noneMatchTest() {
        boolean match = list.stream()
                .noneMatch(sku -> sku.getTotalPrice() > 10000);
        System.out.println(match);
    }

    @Test
    public void findFirstTest () {
        Optional<Sku> optional = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // 串行操作
                .findFirst();
        System.out.println(JSON.toJSONString(optional.get(), true));
    }

    @Test
    public void findAnyTest() {
        Optional<Sku> optional = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // 并行操作 有可能返回值不一致
                .findAny();

        System.out.println(JSON.toJSONString(optional.get(), true));
    }

    @Test
    public void maxTest() {
        OptionalDouble optionalDouble = list.stream()
                //获取总价
                .mapToDouble(Sku::getTotalPrice)

                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    @Test
    public void minTest() {
        OptionalDouble min = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(min.getAsDouble());
    }

    @Test
    public void countTest() {
        long count = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .count();
        System.out.println(count);
    }
}
