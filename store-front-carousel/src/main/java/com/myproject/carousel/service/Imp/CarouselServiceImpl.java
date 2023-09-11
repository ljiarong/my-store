package com.myproject.carousel.service.Imp;/**
 * ClassName: CarouselServiceImpl
 * Package: com.myproject.carousel.service.Imp
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.carousel.mapper.CarouselMapper;
import com.myproject.carousel.service.CarouselService;
import com.myproject.pojo.Carousel;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: my-store
 *
 * @description: 实现类
 *
 * @author: ljr
 *
 * @create: 2023-07-21 11:23
 **/
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Cacheable(value = "list.carousel",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> carouselQueryWrapper=new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority").last("limit 6");
        List<Carousel> result = carouselMapper.selectList(carouselQueryWrapper);

//        result.stream().limit(6).collect(Collectors.toList());
        log.info("CarouselServiceImpl执行结束，结果{查询成功}");
        return R.ok(result);
    }
}
