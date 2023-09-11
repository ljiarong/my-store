package com.myproject.listener;/**
 * ClassName: RabbitMqListener
 * Package: com.myproject.listener
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.doc.ProductDoc;
import com.myproject.pojo.Product;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: my-store
 *
 * @description: 消息队列监听类
 *
 * @author: ljr
 *
 * @create: 2023-09-10 23:43
 **/
@Component
public class RabbitMqListener {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //监听并且插入数据的方法
    @RabbitListener(
            bindings = {
                @QueueBinding(
                        value=@Queue(name = "insert.queue"),
                        exchange =@Exchange("topic.ex"),
                        key = "product.insert"
                )
            }
    )  //这整个注解是在这个insert方法上的
    public void insert(Product product) throws IOException {
        IndexRequest indexRequest=new IndexRequest("product").id(product.getProductId().toString());
        ObjectMapper objectMapper=new ObjectMapper();
        String s = objectMapper.writeValueAsString(new ProductDoc(product));
        indexRequest.source(s, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value=@Queue(name = "remove.queue"),
                            exchange =@Exchange("topic.ex"),
                            key = "product.remove"
                    )
            }
    )
    public void remove(Integer productId) throws IOException {
        DeleteRequest deleteRequest=new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
    }
}
