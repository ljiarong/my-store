package com.myproject.cart.service.Impl;/**
 * ClassName: CartServiceImpl
 * Package: com.myproject.cart.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.cart.mapper.CartMapper;
import com.myproject.cart.service.CartService;
import com.myproject.clients.ProductClient;
import com.myproject.pojo.Cart;
import com.myproject.pojo.Product;
import com.myproject.request.CartSaveRequest;
import com.myproject.request.CartUpdateRequest;
import com.myproject.request.ProductIdListRequest;
import com.myproject.request.ProductIdRequest;
import com.myproject.utils.R;
import com.myproject.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @create: 2023-09-11 22:45
 **/
@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductClient productClient;
    @Override
    public R save(CartSaveRequest cartSaveRequest) {
        ProductIdRequest productIdRequest = new ProductIdRequest();
        productIdRequest.setProductID(cartSaveRequest.getProductId());


        Product product = productClient.productDetail(productIdRequest);
        if (product==null) {
            return R.fail("商品已被删除，无法添加");
        }
        if (product.getProductNum()==0){
            R ok=R.ok("没有库存,添加购物车失败");
            ok.setCode("003");
            return ok;
        }
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",cartSaveRequest.getUserId()).eq("product_id",cartSaveRequest.getProductId());
        Cart cart = cartMapper.selectOne(cartQueryWrapper);
        if (cart!=null) {
            if(cart.getNum()<product.getProductNum()) {
                cart.setNum(cart.getNum() + 1);
                cartMapper.updateById(cart);
                R ok = R.ok("购物车存在该商品，数量+1");
                ok.setCode("002");
                return ok;
            }
            else {
                R ok=R.ok("没有库存，添加失败");
                ok.setCode("003");
                return ok;
            }
        }
        Cart cartInsert=new Cart();
        cartInsert.setProductId(cartSaveRequest.getProductId());
        cartInsert.setUserId(cartSaveRequest.getUserId());
        cartInsert.setNum(1);
        int row = cartMapper.insert(cartInsert);
        CartVo cartVo=new CartVo(product,cartInsert);    //这里取名要注意问题，不能写成cart
        R r = R.ok("购物车添加成功",cartVo);
        return r;
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",userId);
        List<Cart> carts = cartMapper.selectList(cartQueryWrapper);
        if (carts.size()==0 || carts==null) {
            carts=new ArrayList<>();
            return R.ok("购物车空空如也",carts);
        }
        List<CartVo> cartVos=new ArrayList<>();
        List<Integer> ids=new ArrayList<>();
        for (Cart cart : carts) {
            ids.add(cart.getProductId());
        }
        ProductIdListRequest productIdListRequest=new ProductIdListRequest();
        productIdListRequest.setProductIdList(ids);
        List<Product> productList = productClient.productListById(productIdListRequest);
        Map<Integer, Product> collect = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));//这里的collectors.tomap的参数是两个函数，v->v算一个函数
//这里的v->v是lambda表达式，即输入v输出v,由于这里输入的是product，所以输出的也是product
        for (Cart cart : carts) {
            CartVo cartVo = new CartVo(collect.get(cart.getProductId()),cart);
            cartVos.add(cartVo);
        }
        R r = R.ok("数据库数据查询成功", cartVos);
        return r;
    }

    @Override
    public R updateCart(CartUpdateRequest cartUpdateRequest) {
        ProductIdRequest productIdRequest=new ProductIdRequest();
        productIdRequest.setProductID(cartUpdateRequest.getProductId());
        Product product = productClient.productDetail(productIdRequest);
        if (cartUpdateRequest.getNum()>product.getProductNum()){
            return R.fail("修改失败，超出库存");
        }
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("product_id",cartUpdateRequest.getProductId()).eq("user_id",cartUpdateRequest.getUserId());
        Cart cartResult = cartMapper.selectOne(cartQueryWrapper);
        cartResult.setNum(cartUpdateRequest.getNum());
        int row = cartMapper.updateById(cartResult);
        log.info("CartServiceImpl执行结束，结果{updateCart}"+row);

        return R.ok("修改购物车数量成功");
    }

    @Override
    public R removeCart(CartSaveRequest cartSaveRequest) {
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("product_id",cartSaveRequest.getProductId()).eq("user_id", cartSaveRequest.getUserId());
        int delete = cartMapper.delete(cartQueryWrapper);
        log.info("CartServiceImpl执行结束，结果{removeCart}"+delete);
        return R.ok("删除购物车成功");
    }

    @Override
    public void clearIds(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
        log.info("CartServiceImpl执行结束，结果{clearIds}",cartIds);
    }

    @Override
    public Long productCount(Integer productId) {

        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("product_id",productId);
        Long count = cartMapper.selectCount(cartQueryWrapper);
        return count;
    }
}
