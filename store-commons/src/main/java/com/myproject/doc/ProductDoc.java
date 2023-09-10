package com.myproject.doc;/**
 * ClassName: ProductDoc
 * Package: com.myproject.doc
 */

import com.myproject.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: my-store
 *
 * @description: 商品搜索实体类
 *
 * @author: ljr
 *
 * @create: 2023-09-10 00:16
 **/
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(), product.getProductTitle(), product.getProductIntro(), product.getProductPicture(), product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(), product.getProductSales());
        this.all=product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
