package com.leave996.leave996.cart;

import com.leave996.leave996.cart.Sku;

/**
 * 对Sku的总价是否超出2000作为判断逻辑
 */
public class SkuTotalPricePredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000;
    }
}
