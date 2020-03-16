package com.leave996.leave996.lambda.cart;

/**
 * 商品类型meiju
 */
public enum  SkuCategoryEnum {
    CLOTHING(10, "服装类"),
    EXECTRONICS(20, "数码类"),
    SPORTS(30, "运动类"),
    BOOKS(40, "图书类")
    ;

    //产品类型编号
    private Integer code;

    // 产品类型名称
    private String name;

    SkuCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
