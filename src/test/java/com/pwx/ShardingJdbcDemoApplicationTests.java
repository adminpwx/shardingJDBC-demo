package com.pwx;

import com.pwx.mapper.CityMapper;
import com.pwx.mapper.OrderMapper;
import com.pwx.pojo.City;
import com.pwx.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ShardingJdbcDemoApplicationTests {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    void testAdd() {
//        HintManager hintManager = HintManager.getInstance();
//        // 通过MyHintShardingAlgorithm可知，强制路由到ds${value%2}
//        //直接指定数据库
//        hintManager.setDatabaseShardingValue(1L);
//        hintManager.addDatabaseShardingValue("t_city",0);
        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.setName("test_商丘市" + i);
            city.setProvince("test_河南省" + i);
            cityMapper.insert(city);
        }
    }

    @Test
    void testSelect() {
        cityMapper.selectList(null);
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
