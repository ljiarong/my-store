package com.myproject.collection.controller;/**
 * ClassName: CollectionController
 * Package: com.myproject.collection.controller
 */

import com.myproject.collection.service.CollectionService;
import com.myproject.pojo.Collect;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 20:52
 **/
@RestController
@RequestMapping("collect")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @PostMapping("save")
    public R save(@RequestBody @Validated Collect collect, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("收藏的userId和productId不能为空");
        }
        return collectionService.save(collect);

    }

    @PostMapping("list")
    public R getCollectList(@RequestBody @Validated UserIdRequest userIdRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("用户id不能为空");
        }
        return collectionService.getCollectList(userIdRequest);
    }

    @PostMapping("remove")
    public R remove(@RequestBody @Validated Collect collect,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("移除失败，参数不能为空");
        }
        return collectionService.remove(collect);
    }

    @PostMapping("remove/product")
    public R removeByProductId(@RequestBody Integer productId){
        return collectionService.removeByProductId(productId);
    }
}
