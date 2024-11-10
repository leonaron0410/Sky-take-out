package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品口味的Mapper持久层类
 */
@Mapper
public interface DishFlavorMapper {

    List<DishFlavor> getByDishId(Long dishId);

    void saveBatch(List<DishFlavor> flavors);

    void deleteByDishId(Long id);
}
