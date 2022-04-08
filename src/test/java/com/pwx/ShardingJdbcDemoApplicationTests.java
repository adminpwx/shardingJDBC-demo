package com.pwx;

import com.pwx.mapper.CityMapper;
import com.pwx.mapper.OrderMapper;
import com.pwx.pojo.City;
import com.pwx.pojo.Order;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class ShardingJdbcDemoApplicationTests {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    void testAdd() {
        HintManager hintManager = HintManager.getInstance();
        // 通过MyHintShardingAlgorithm可知，强制路由到ds-${value%2}
        hintManager.setDatabaseShardingValue(1L);
        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.setId((long)i);
            city.setName("test_1_商丘市" + i);
            city.setProvince("test_1_河南省" + i);
            cityMapper.insert(city);
        }
    }

    @Test
    void OrderInsert(){
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOrderNo("test");
            order.setUserId((long)i);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setPrice(0.00);
            orderMapper.insert(order);
        }
    }
}
