package com.myproject.user.service;

import com.myproject.pojo.Address;
import com.myproject.request.AddressListRequest;
import com.myproject.request.AddressRequest;
import com.myproject.utils.R;

/**
 * ClassName: AddressService
 * Package: com.myproject.user.service
 */
public interface AddressService {
    R addressList(Integer userId);

    R addAddress(AddressRequest address);

    R removeAddress(Integer id);
}
