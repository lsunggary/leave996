package com.leave996.leave996.stream;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.leave996.leave996.lambda.cart.CartService;
import com.leave996.leave996.lambda.cart.Sku;
import com.leave996.leave996.lambda.cart.SkuCategoryEnum;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 对比： 原始集合操作与Steam集合操作
 */
public class StreamVS {

    /**
     * 1. 想看看购物车中都有神马东西
     * 2. 图书类商品都会买
     * 3. 其余商品中两件最贵的
     * 4. 只需要两件商品的名称和总价
     */

    /**
     * 以原始集合操作实现需求
     */
    @Test
    public void oldCartHandle() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        /**
         * 1 打印所有商品
         */
        for (Sku sku: cartSkuList
             ) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        List<Sku> notBookSkuList = new ArrayList<>();

        /**
         * 2 图书类过滤掉
         */
        for (Sku sku: cartSkuList
             ) {
            if (!sku.getSkuCategory().equals(SkuCategoryEnum.BOOKS)) {
                notBookSkuList.add(sku);
            }
        }

        /**
         * 排序
         */
        notBookSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                if (o1.getTotalPrice() > o2.getTotalPrice()) {
                    return -1;
                } else if (o1.getTotalPrice() < o2.getTotalPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        /**
         * Top2
         */
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i =0; i<2; i++) {
            top2SkuList.add(notBookSkuList.get(i));
        }

        /**
         * 4 求两件商品的总价
         */
        Double money = 0.0;
        for (Sku sku: top2SkuList
             ) {
            money += sku.getTotalPrice();
        }

        /**
         * 5 获取两件商品的名称
         */
        List<String> resultSkuNameList = new ArrayList<>();
        for (Sku sku: top2SkuList
             ) {
            resultSkuNameList.add(sku.getSkuName());
        }

        /**
         * 打印输出
         */

        System.out.println(
                JSON.toJSONString(resultSkuNameList, true)
        );
        System.out.println(
                "总价为：" + money
        );
    }

    /**
     * 以stream流实现
     */
    @Test
    public void newCartHandle() {
        AtomicReference<Double> money =
                new AtomicReference<>(Double.valueOf(0.0));

        List<String> resultSkuNameList =
                CartService.getCartSkuList()
                .stream()
                /**
                 * 1,答应商品信息
                 */
                .peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                /**
                 * 过滤掉图书类商品
                 */
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                /**
                 * 排序
                 */
                .sorted(Comparator
                        .comparing(Sku::getTotalPrice).reversed())
                /**
                 * TOP2
                 */
                .limit(2)
                /**
                 * 对金额进行累加
                 */
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                /**
                 * 获取商品名称
                 */
                .map(sku -> sku.getSkuName())
                /**
                 * 对结果进行收集
                 */
                .collect(Collectors.toList());

        /**
         * 打印输出
         */

        System.out.println(
                JSON.toJSONString(resultSkuNameList, true)
        );
        System.out.println(
                "总价为：" + money.get()
        );

    }
}
