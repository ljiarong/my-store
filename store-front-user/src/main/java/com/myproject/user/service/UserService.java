package com.myproject.user.service;

import com.myproject.pojo.User;
import com.myproject.request.UserCheckRequest;
import com.myproject.request.UserLoginRequest;
import com.myproject.utils.R;

import java.util.Map;

/**
 * ClassName: UserService
 * Package: com.myproject.user.service
 */
public interface UserService {
    R check(UserCheckRequest userCheckRequest);

    R register(User user);

    R login(UserLoginRequest userLoginRequest);
}
