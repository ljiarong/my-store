package com.myproject.order.service.Impl;/**
 * ClassName: OrderServiceImpl
 * Package: com.myproject.order.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myproject.clients.ProductClient;
import com.myproject.message.OrderToProduct;
import com.myproject.order.Mapper.OrderMapper;
import com.myproject.order.service.OrderService;
import com.myproject.pojo.Order;
import com.myproject.pojo.Product;
import com.myproject.request.OrderRequest;
import com.myproject.request.PageRequest;
import com.myproject.request.ProductIdListRequest;
import com.myproject.utils.R;
import com.myproject.vo.CartVo;
import com.myproject.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-13 16:45
 **/
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    ProductClient productClient;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
    * @Author: ljr
    * @Description: 1.将购物车数据转为订单数据 2.进行订单数据的批量插入 3.商品库存修改消息 4.发送购物车库存修改消息
    * @DateTime: 2023/9/13
    * @Params:
    * @Return
    */
    @Override
    @Transactional  //事务注解，要么全部成功，要么全部失败
    public R saveOrder(OrderRequest orderRequest) {

        List<Integer>   cartIds=new ArrayList<>();
        List<OrderToProduct> orderToProducts=new ArrayList<>();
        List<Order> orders=new ArrayList<>();

        Integer userId= orderRequest.getUserId();
        Long orderTime=System.currentTimeMillis();

        for (CartVo cartVo : orderRequest.getProducts()) {
            cartIds.add(cartVo.getId());
            OrderToProduct orderToProduct=new OrderToProduct();
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProduct.setNum(cartVo.getNum());
            orderToProducts.add(orderToProduct);
            Order order=new Order();
            order.setOrderTime(orderTime);
            order.setOrderId(orderTime);   //TODO:这里orderId图省事直接用的ordertime会导致间隔过短时将两个订单合在一起，需要另外生成orderid
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            order.setUserId(userId);
            orders.add(order);
        }
        saveBatch(orders);

        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);


        return R.ok("订单保存成功");
    }

    @Override
    public R orderList(Integer userId) {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        orderQueryWrapper.eq("user_id",userId);
        List<Order> orderList = list(orderQueryWrapper);

        Map<Long, List<Order>> longListMap = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));//按orderid进行分组

        List<Integer> productIds = orderList.stream().map(Order::getProductId).collect(Collectors.toList());//取出商品id

        ProductIdListRequest productIdListRequest = new ProductIdListRequest();
        productIdListRequest.setProductIdList(productIds);
        List<Product> productList = productClient.productListById(productIdListRequest);
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        List<List<OrderVO>> result=new ArrayList<>();

        for (List<Order> orders : longListMap.values()) {
            List<OrderVO> orderVOList=new ArrayList<>();
            for (Order order : orders) {
                OrderVO orderVO=new OrderVO();
                BeanUtils.copyProperties(order,orderVO);
                Product product = productMap.get(order.getProductId());
                orderVO.setProductName(product.getProductName());
                orderVO.setProductPicture(product.getProductPicture());
                orderVOList.add(orderVO);
            }
            result.add(orderVOList);
        }
        R ok = R.ok("订单数据获取成功", result);
        log.info("OrderServiceImpl执行结束，结果{orderList}",ok);

        return ok;
    }

    @Override
    public Long productCount(Integer productId) {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        orderQueryWrapper.eq("product_id",productId);
        long count = count(orderQueryWrapper);
        return count;
    }

    @Override
    public R orderListGroup(PageRequest pageRequest) {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        orderQueryWrapper.select("order_id","user_id","COUNT(*) as product_num","SUM(product_num) as order_num","SUM(product_num * product_price) as order_price","order_time").groupBy("order_id");
//        IPage<OrderListVo> listVoIPage=new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());  //也可以自定义vo
        IPage<Map<String,Object>> listVoIPage=new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        listVoIPage=baseMapper.selectMapsPage(listVoIPage,orderQueryWrapper);
        return R.ok("订单查询成功",listVoIPage.getRecords(),listVoIPage.getTotal());
    }
}
