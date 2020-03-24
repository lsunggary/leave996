package com.leave996.leave996.cart;

import java.util.ArrayList;
import java.util.List;

import com.leave996.leave996.cart.SkuPredicate;
import com.leave996.leave996.cart.Sku;
import com.leave996.leave996.lambda.cart.SkuCategoryEnum;

/**
 * 购物车服务类
 */
public class CartService {

    private static List<Sku> cartSkuList = new ArrayList<Sku>() {
        {
            add(new Sku(4521362, "无人机", 4999.00, 1, 4999.00, SkuCategoryEnum.EXECTRONICS));
            add(new Sku(4521363, "VR一体机", 3999.00, 1, 3999.00, SkuCategoryEnum.EXECTRONICS));
            add(new Sku(4521364, "JAVA核心技术", 100.00, 1, 100.00, SkuCategoryEnum.BOOKS));
            add(new Sku(4521365, "纯色衬衫", 89.00, 3, 267.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(4521366, "跑步机", 1200.00, 1, 1200.00, SkuCategoryEnum.SPORTS));
            add(new Sku(4521367, "运动手环", 123.00, 1, 123.00, SkuCategoryEnum.SPORTS));
            add(new Sku(4521368, "牛仔裤", 120.00, 1, 120.00, SkuCategoryEnum.CLOTHING));
        }
    };

    /**
     * 获取sku列表
     * @return
     */
    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }

    /**
     * Version 1.0.0
     * 找出购物车中所有电子产品
     * @param cartSkuList
     * @return
     */
    public List<Sku> filterElectronicsSkus(List<Sku> cartSkuList) {
        List<Sku> result = new ArrayList<>();

        for (Sku sku: cartSkuList) {
            if (SkuCategoryEnum.EXECTRONICS.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 2.0.0
     * 根据传入商品类型参数，找出购物车中同类商品类型的商品列表
     * @param cartSkukList
     * @param category
     * @return
     */
    public static List<Sku> filterSkusByCategory(
            List<Sku> cartSkukList, SkuCategoryEnum category
    ) {
        List<Sku> result = new ArrayList<>();

        for (Sku sku: cartSkuList) {
            if (category.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 3.0.0
     * 根据传入商品类型参数，找出购物车中同类商品类型的商品列表
     * @param cartSkukList
     * @param category
     * @return
     */
    public static List<Sku> filterSkusByCategoryAndPrice(
            List<Sku> cartSkukList, SkuCategoryEnum category,
            Double totalPrice, Boolean categoryOrPrice
    ) {
        List<Sku> result = new ArrayList<>();

        for (Sku sku: cartSkuList) {
            if (categoryOrPrice && category.equals(sku.getSkuCategory())
                ||
                (categoryOrPrice && sku.getTotalPrice() > totalPrice)
            ) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 4.0.0
     * 根据不同的SKU判断标准 对SKU进行过滤
     * @param cartSkuList
     * @param predicate 不同的SKU判断策略
     * @return
     */
    public static List<Sku> filterSkus(
            List<Sku> cartSkuList, SkuPredicate predicate)  {
        List<Sku> result = new ArrayList<>();

        for (Sku sku: cartSkuList) {
            // 根据不同的Sku判断标准策略，对Sku进行判断
            if (predicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
}
