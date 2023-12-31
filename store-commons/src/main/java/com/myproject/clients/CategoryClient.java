package com.myproject.clients;

import com.myproject.pojo.Category;
import com.myproject.request.CategoryListRequest;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: CategoryClient
 * Package: com.myproject.clients
 */
@FeignClient("category-service")   //将api转成网络服务，指定服务名称    这个是用spring-cloud来完成的
public interface CategoryClient {
    @GetMapping("/category/promo/{categoryName}")
    R getIdByName(@PathVariable String categoryName);

    @PostMapping("/category/hots")
    R hots(@RequestBody CategoryListRequest categoryListRequest);
    @GetMapping("/category/list")
    R list();

    @PostMapping("/admin/category/list")
    R adminCategoryList(@RequestBody PageRequest pageRequest);

    @PostMapping("/admin/category/save")
    R categorySave(@RequestBody Category category);

    @PostMapping("/admin/category/delete")
    R categoryDelete(@RequestBody Integer categoryId);
    @PostMapping("/admin/category/update")
    R categoryUpdate(@RequestBody Category category);

}
