package com.leave996.leave996.stream;

import com.alibaba.fastjson.JSON;
import com.leave996.leave996.cart.CartService;
import com.leave996.leave996.cart.Sku;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常见的预定义收集器使用
 */
public class StreamCollector {

    /**
     * 使用Collectors.toList()将元数据收集成一个数组
     */
    @Test
    public void toList() {
        List<Sku> list = CartService.getCartSkuList();

        List<Sku> result = list.stream()
                .filter(sku -> sku.getTotalPrice() > 100)

                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(result, true));
    }

    /**
     * 对现有属性进行分组汇总
     */
    @Test
    public void group() {
        List<Sku> list = CartService.getCartSkuList();
        Map<Object, List<Sku>> map = list.stream()
                .collect(
                        Collectors.groupingBy(
                                sku -> sku.getSkuCategory()));

        System.out.println(JSON.toJSONString(map, true));
    }

    /**
     * 分区，分组的特例
     * 以false为一个列表
     * 以true为一个列表
     */
    @Test
    public void partition() {
        List<Sku> list = CartService.getCartSkuList();
        Map<Boolean, List<Sku>> map = list.stream()
                .collect(Collectors.partitioningBy(sku -> sku.getTotalPrice() > 100));

        System.out.println(JSON.toJSONString(map, true));
    }
}
