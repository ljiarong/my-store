package com.myproject.carousel.controller;/**
 * ClassName: CarouselController
 * Package: com.myproject.carousel.controller
 */

import com.myproject.carousel.service.CarouselService;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description: 控制器
 *
 * @author: ljr
 *
 * @create: 2023-07-21 11:20
 **/
@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }
}
