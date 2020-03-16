package com.leave996.leave996.cart;

import com.leave996.leave996.lambda.cart.Sku;
/**
 * Sku选择谓词接口
 */
public interface SkuPredicate {

    // 重写 自由定义Sku
    boolean test(Sku sku);
}
