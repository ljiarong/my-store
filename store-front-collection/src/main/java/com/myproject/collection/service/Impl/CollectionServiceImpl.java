package com.myproject.collection.service.Impl;/**
 * ClassName: CollectionServiceImpl
 * Package: com.myproject.collection.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.clients.ProductClient;
import com.myproject.collection.mapper.CollectionMapper;
import com.myproject.collection.service.CollectionService;
import com.myproject.pojo.Collect;
import com.myproject.request.ProductIdListRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 21:00
 **/
@Service
@Slf4j
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    ProductClient productClient;
    @Override
    public R save(Collect collect) {
        QueryWrapper<Collect> collectQueryWrapper=new QueryWrapper<>();
        collectQueryWrapper.eq("user_id",collect.getUserId()).eq("product_id",collect.getProductId());
        if (collectionMapper.selectCount(collectQueryWrapper)>0) {
            return R.fail("已经收藏，添加失败");
        }
        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectionMapper.insert(collect);
        log.info("CollectionServiceImpl执行结束，结果{save}",rows);
        return R.ok("收藏添加成功",rows);
    }

    @Override
    public R getCollectList(UserIdRequest userIdRequest) {
        QueryWrapper<Collect> collectQueryWrapper=new QueryWrapper<>();
        collectQueryWrapper.eq("user_id",userIdRequest.getUserId()).select("product_id");
        List<Object> objects = collectionMapper.selectObjs(collectQueryWrapper);
        List<Integer> ids=new ArrayList<>();
        for (Object o : objects) {
            ids.add((Integer) o);
        }
        ProductIdListRequest productIdListRequest=new ProductIdListRequest();
        productIdListRequest.setProductIdList(ids);


        R results = productClient.getProductListById(productIdListRequest);
        return results;
    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> collectQueryWrapper=new QueryWrapper<>();
        collectQueryWrapper.eq("user_id",collect.getUserId()).eq("product_id",collect.getProductId());
        int rows = collectionMapper.delete(collectQueryWrapper);
        log.info("CollectionServiceImpl执行结束，结果{remove}",rows);
        return R.ok("收藏删除成功");
    }
}
