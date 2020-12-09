package com.homework.demo.xa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homework.demo.xa.entity.Inventory;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品库存信息表 Mapper 接口
 * </p>
 */
@Repository
public interface InventoryMapper extends BaseMapper<Inventory> {

}
