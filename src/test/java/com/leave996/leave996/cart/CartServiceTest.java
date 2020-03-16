package com.leave996.leave996.cart;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import com.leave996.leave996.lambda.cart.Sku;
import com.leave996.leave996.lambda.cart.CartService;

class CartServiceTest {

    /**
     * Version 1.0.0
     */
    @Test
    void filterElectronicsSkus() {
    }

    /**
     * Version 2.0.0
     */
    @Test
    void filterSkusByCategory() {
    }

    /**
     * Version 3.0.0
     */
    @Test
    void filterSkusByCategoryAndPrice() {
    }

    /**
     * Version 4.0.0
     */
    @Test
    void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());

        System.out.println(JSON.toJSONString(result, true));
    }

    /**
     * Version 5.0.0
     * 匿名内部类
     */
    @Test
    void filterSkusWithInnerClass() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuPredicate() {
                    @Override
                    public boolean test(Sku sku) {
                        return sku.getTotalPrice() > 1000;
                    }
                }
        );

        System.out.println(JSON.toJSONString(result, true));
    }

    /**
     * Version 6.0.0
     * lambda
     */
    @Test
    void filterSkusWithLambda() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(
                cartSkuList,
                (Sku sku) -> sku.getTotalPrice() > 1000);

        System.out.println(JSON.toJSONString(result, true));
    }
}
