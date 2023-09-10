package com.myproject.listener;/**
 * ClassName: SpringBootListener
 * Package: com.myproject.listener
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.clients.ProductClient;
import com.myproject.doc.ProductDoc;
import com.myproject.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: my-store
 *
 * @description: 监听程序
 *
 * @author: ljr
 *
 * @create: 2023-09-10 19:24
 **/
@Slf4j
@Component
public class SpringBootListener implements ApplicationRunner {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ProductClient productClient;

    private String index="{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
        * @Author: ljr
        * @Description: 判断product索引是否存在，存在则删除，然后创建
        * @DateTime: 2023/9/10
        * @Params: 
        * @Return 
        */
        GetIndexRequest getIndexRequest=new GetIndexRequest("product");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (exists) {
            DeleteByQueryRequest deleteByQueryRequest=new DeleteByQueryRequest("product");
            deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());//全部删除
            restHighLevelClient.deleteByQuery(deleteByQueryRequest,RequestOptions.DEFAULT);

        }
        else {

            CreateIndexRequest createIndexRequest=new CreateIndexRequest("product");
            createIndexRequest.source(index, XContentType.JSON);  //索引类型
            restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        }
        List<Product> allProduct = productClient.getAllProduct();   //获取全部数据


        //数据插入
        BulkRequest bulkRequest=new BulkRequest();

        ObjectMapper objectMapper=new ObjectMapper();

        for (Product product : allProduct) {
            ProductDoc doc = new ProductDoc(product);
            IndexRequest indexRequest=new IndexRequest("product").id(product.getProductId().toString());   //用于插入数据
            //将productdoc转为json传入
            String json = objectMapper.writeValueAsString(doc);
            indexRequest.source(json,XContentType.JSON);    //将productdoc转为json放入
            bulkRequest.add(indexRequest);   //将index加入bulkrequest
        }
        restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);//批量执行bulkrequest
    }
}
