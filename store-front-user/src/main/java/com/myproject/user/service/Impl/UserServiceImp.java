package com.myproject.user.service.Impl;/**
 * ClassName: UserService
 * Package: com.myproject.user.service
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.UserCheckRequest;
import com.myproject.request.UserLoginRequest;
import com.myproject.user.mapper.UserMapper;
import com.myproject.user.service.UserService;
import com.myproject.utils.MD5Util;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: my-store
 *
 * @description: 服务层
 *
 * @author: ljr
 *
 * @create: 2023-07-19 14:01
 **/
@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public R check(UserCheckRequest userCheckRequest) {
        //封装查询参数(条件)
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userCheckRequest.getUserName());
        //查询结果
        Long selectCount = userMapper.selectCount(userQueryWrapper);
        if(selectCount==0){
            log.info("UserService execute down,result{账号不存在}");
            return R.ok("账号不存在");
        }

        log.info("UserService execute down,result{账号存在}");
        return R.fail("账号存在");
    }

    /**
    * @Author: ljr
    * @Description: 1.账号检验 2.密码加密(加盐) 3.插入数据 4.返回结果
    * @DateTime: 2023/7/19
    * @Params: 
    * @Return 
    */
    @Override
    public R register(User user) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_name",user.getUserName());
        Long result = userMapper.selectCount(userQueryWrapper);
        if(result>0){
            log.info("UserServiceImp执行结束，结果{账号已经存在，注册失败}");
            return R.fail("账号已经存在，注册失败");
        }
        //账号加密(md5)
        String password = MD5Util.encode(user.getPassword() + MD5Util.USER_SALT);
        user.setPassword(password);
        int i = userMapper.insert(user);
        if(i==0){
            log.info("UserServiceImp执行结束，结果{数据库插入失败}");
            return R.fail("注册失败，稍后再试");
        }
        log.info("UserServiceImp执行结束，结果{注册成功}");
        return R.ok("注册成功");
    }

    /**
    * @Author: ljr
    * @Description: 1.密码加密 2.查询数据库 3.返回结果
    * @DateTime: 2023/7/19
    * @Params: 
    * @Return 
    */
    @Override
    public R login(UserLoginRequest userLoginRequest) {
        String checkPassword = MD5Util.encode(userLoginRequest.getPassword() + MD5Util.USER_SALT);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userLoginRequest.getUserName());
        userQueryWrapper.eq("PASSWORD",checkPassword);
        User userResult = userMapper.selectOne(userQueryWrapper);
        if(userResult==null){
            log.info("UserServiceImp执行结束，结果{账号密码错误}");
            return R.fail("账号或密码错误");
        }
        log.info("UserServiceImp执行结束，结果{登录成功}");
        userResult.setPassword(null);
//        userResult.setUserPhonenumber(null);
        return R.ok("登录成功",userResult);
    }

    @Override
    public R userList(PageRequest pageRequest) {
        IPage<User> userIPage=new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        IPage<User> userIPage1 = userMapper.selectPage(userIPage, null);
        List<User> records = userIPage1.getRecords();
        long total = userIPage1.getTotal();


        return R.ok("用户查询成功",records,total);
    }

    @Override
    public R userRemove(Integer userId) {
        int row = userMapper.deleteById(userId);
        log.info("UserServiceImp执行结束，结果{userRemove}",row);
        return R.ok("用户数据删除成功");
    }


    //判断是否为原密码   将密码加密
    @Override
    public R userUpdate(User user) {
        User selectById = userMapper.selectById(user.getUserId());
        if (user==null){
            return R.fail("修改失败,未找到该用户");
        }
        else if(StringUtils.equals(user.getPassword(),selectById.getPassword())){
            userMapper.updateById(user);
            return R.ok("修改成功");
        }
        else if (StringUtils.equals(selectById.getPassword(), MD5Util.encode(user.getPassword()+MD5Util.USER_SALT))) {
            return R.fail("修改失败,输入的密码与之前相同");
        }
        user.setPassword(MD5Util.encode(user.getPassword()+MD5Util.USER_SALT));
        userMapper.updateById(user);
        return R.ok("修改成功");
    }

    @Override
    public R userSave(User user) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_name",user.getUserName());
        Long result = userMapper.selectCount(userQueryWrapper);
        if(result>0){
            log.info("UserServiceImp执行结束，结果{账号已经存在，添加失败失败}");
            return R.fail("账号已经存在，添加失败");
        }
        //账号加密(md5)
        String password = MD5Util.encode(user.getPassword() + MD5Util.USER_SALT);
        user.setPassword(password);
        int i = userMapper.insert(user);
        if(i==0){
            log.info("UserServiceImp执行结束，结果{数据库插入失败}");
            return R.fail("添加失败，稍后再试");
        }
        log.info("UserServiceImp执行结束，结果{添加成功}");
        return R.ok("添加成功");
    }
}
