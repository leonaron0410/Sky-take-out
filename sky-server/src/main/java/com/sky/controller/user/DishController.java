package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        //先查询redis中是否有数据
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get("dish_" + categoryId);
        if (list != null && list.size() > 0) {
            log.info("从redis中获取菜品数据");
            return Result.success(list);
        }
        //如果redis中没有，则从数据库中拿去数据
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品
        log.info("根据分类id查询菜品{},存储到了数据库", categoryId);
        list = dishService.listWithFlavor(dish);
        //将拿取的数据保存在reids中
        redisTemplate.opsForValue().set("dish_" + categoryId, list);
        return Result.success(list);
    }

}
