package com.pwx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pwx.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author qjc
 * @since 2021-03-25
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
