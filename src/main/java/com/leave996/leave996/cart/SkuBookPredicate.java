package com.leave996.leave996.cart;

import com.leave996.leave996.lambda.cart.SkuCategoryEnum;

public class SkuBookPredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
    }
}
