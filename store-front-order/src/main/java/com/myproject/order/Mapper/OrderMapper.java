package com.myproject.order.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myproject.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.myproject.order.Mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
