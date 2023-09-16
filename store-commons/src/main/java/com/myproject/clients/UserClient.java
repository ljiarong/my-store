package com.myproject.clients;

import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: UserClient
 * Package: com.myproject.clients
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("user/admin/list")
    public R userList(@RequestBody PageRequest pageRequest);  //feign传的是json
    @PostMapping("user/admin/remove")
    public R userRemove(@RequestBody UserIdRequest userIdRequest);

    @PostMapping("user/admin/update")
    public R userUpdate(@RequestBody User user);

    @PostMapping("user/admin/save")
    public R userSave(@RequestBody User user);
}
