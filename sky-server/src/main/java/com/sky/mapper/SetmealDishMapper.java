package com.sky.mapper;

import com.sky.entity.SetmealDish;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    void saveBatch(List<SetmealDish> setmealDishes);

    void deleteBySetmealIds(Long id);

    List<SetmealDish> getSetmealDishesBySetmealId(Long id);
}
