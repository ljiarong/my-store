package com.myproject.collection.service;

import com.myproject.pojo.Collect;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;

/**
 * ClassName: CollectionService
 * Package: com.myproject.collection.service
 */
public interface CollectionService {
    R save(Collect collect);

    R getCollectList(UserIdRequest userIdRequest);

    R remove(Collect collect);
}
