package com.myproject.user.service.Impl;/**
 * ClassName: AddressServiceImp
 * Package: com.myproject.user.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.pojo.Address;
import com.myproject.request.AddressListRequest;
import com.myproject.user.mapper.AddressMapper;
import com.myproject.user.service.AddressService;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: my-store
 *
 * @description: 地址服务实现
 *
 * @author: ljr
 *
 * @create: 2023-07-20 10:15
 **/
@Service
@Slf4j
public class AddressServiceImp implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public R addressList(Integer userId) {
        QueryWrapper<Address> addressQueryWrapper=new QueryWrapper<>();
        addressQueryWrapper.eq("user_id",userId);
        List<Address> results=addressMapper.selectList(addressQueryWrapper);
        log.info("AddressServiceImp执行结束，结果{查询成功}");
        return R.ok("查询成功",results);
    }

    @Override
    public R addAddress(Address address) {
        int i = addressMapper.insert(address);
        if(i==0){
            log.info("AddressServiceImp执行结束，结果{插入失败}");
            return R.fail("插入失败");
        }
        return addressList(address.getId());
    }

    @Override
    public R removeAddress(Integer id) {
        int rows = addressMapper.deleteById(id);
        if(rows==0){
            log.info("AddressServiceImp执行结束，结果{删除失败}");
            return R.fail("地址删除失败");
        }
        log.info("AddressServiceImp执行结束，结果{删除成功}");
        return R.ok("地址删除成功");
    }
}
