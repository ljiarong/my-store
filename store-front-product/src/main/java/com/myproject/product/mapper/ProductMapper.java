package com.myproject.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myproject.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: com.myproject.product.mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
