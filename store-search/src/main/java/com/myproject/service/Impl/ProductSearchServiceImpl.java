package com.myproject.service.Impl;/**
 * ClassName: ProductSearchServiceImpl
 * Package: com.myproject.service.Impl
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.pojo.Product;
import com.myproject.request.ProductSearchRequest;
import com.myproject.service.ProductSearchService;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-store
 *
 * @description: 实现类
 *
 * @author: ljr
 *
 * @create: 2023-09-10 21:58
 **/
@Service
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    /**
    * @Author: ljr
    * @Description: 1.关键字是否为空 2.分页 3.es查询 4.结果处理
    * @DateTime: 2023/9/10
    * @Params:
    * @Return
    */
    @Override
    public R searchProduct(ProductSearchRequest productSearchRequest) {
        SearchRequest searchRequest=new SearchRequest("product");
        String search = productSearchRequest.getSearch();
        if (StringUtils.isEmpty(search)) {
            //查询所有   不添加all关键字  (all是copy_to的字段)
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }
        //es的分页语法   分为from  和size
        searchRequest.source().from((productSearchRequest.getCurrentPage()-1)*productSearchRequest.getPageSize());   //from是偏移量
        searchRequest.source().size(productSearchRequest.getPageSize());
        SearchResponse searchResponse=null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }

        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits().value;//查询符合的数量
        SearchHit[] hitsHits = hits.getHits();
        List<Product> productList=new ArrayList<>();


        ObjectMapper objectMapper=new ObjectMapper();//json处理器


        for (SearchHit hitsHit : hitsHits) {
            String sourceAsString = hitsHit.getSourceAsString();
            Product product = null;//将json字符串转为object
            try {
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);
        }
        R ok = R.ok("查询成功", productList,total);
        log.info("ProductSearchServiceImpl执行结束，结果{searchProduct}",ok);

        return ok;
    }
}
