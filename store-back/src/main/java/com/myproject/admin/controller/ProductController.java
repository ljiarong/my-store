package com.myproject.admin.controller;/**
 * ClassName: ProductController
 * Package: com.myproject.admin.controller
 */

import com.myproject.admin.service.AdminService;
import com.myproject.pojo.Product;
import com.myproject.request.ProductSaveRequest;
import com.myproject.request.ProductSearchRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-17 17:44
 **/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private AdminService adminService;
    @GetMapping("list")
    public R productList(ProductSearchRequest productSearchRequest){
        return adminService.productSearch(productSearchRequest);
    }
    @PostMapping("upload")
    public R pictureUpload(@RequestParam("img") MultipartFile multipartFile){
        StringBuilder rootPath=new StringBuilder();
        rootPath.append(System.getProperty("user.dir"));
        rootPath.append("\\store-static\\src\\main\\resources\\public\\imgs\\addpicture\\");
        String fileName=multipartFile.getOriginalFilename();
        String filePath=rootPath.append(fileName).toString();
        File fileResult=new File(filePath);
        if (fileResult.exists()){
            return R.fail("上传失败,图片已经存在,请更改图片名后重试","public/imgs/addpicture/"+fileName);
        }else {
            try {
                multipartFile.transferTo(fileResult);
                return R.ok("图片上传成功","public/imgs/addpicture/"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.fail("图片上传时出现异常","");
            }
        }
    }

    @PostMapping("save")
    public R saveProduct(ProductSaveRequest productSaveRequest){
        return adminService.productSave(productSaveRequest);
    }

    @PostMapping("update")
    public R updateProduct(Product product){
        return adminService.updateProduct(product);
    }

    @PostMapping("remove")
    public R removeProduct(Integer productId){
        return adminService.removeProduct(productId);
    }
}
