package com.myproject.user.controller;/**
 * ClassName: AddressController
 * Package: com.myproject.user.controller
 */

import com.myproject.pojo.Address;
import com.myproject.request.AddressListRequest;
import com.myproject.request.AddressRemoveRequest;
import com.myproject.user.service.AddressService;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
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
 * @description: 地址控制器
 *
 * @author: ljr
 *
 * @create: 2023-07-20 10:09
 **/
@RestController
@RequestMapping("user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListRequest addressList, BindingResult bindingResult){
        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            return R.fail("地址查询失败");
        }
        return addressService.addressList(addressList.getUserId());
    }
    @PostMapping("save")
    public R save(@RequestBody @Validated Address address,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("地址保存失败");
        }
        return addressService.addAddress(address);
    }
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveRequest addressRemoveRequest,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("地址删除失败");
        }
        return addressService.removeAddress(addressRemoveRequest.getId());
    }
}
