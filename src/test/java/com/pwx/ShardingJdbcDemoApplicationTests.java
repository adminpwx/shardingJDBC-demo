package com.pwx;

import com.pwx.mapper.CityMapper;
import com.pwx.mapper.OrderMapper;
import com.pwx.pojo.City;
import com.pwx.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class ShardingJdbcDemoApplicationTests {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    void testAdd() {
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
    void testHintAdd() {
        HintManager hintManager = HintManager.getInstance();
        //分库不分表情况下，强制路由至某一个分库时，可使用hintManager.setDatabaseShardingValue方式添加分片
//        hintManager.setDatabaseShardingValue(1L);
        //添加数据源分片键值。
        hintManager.addDatabaseShardingValue("t_city",1);
//        for (int i = 0; i < 10; i++) {
//            City city = new City();
//            city.setName("test_商丘市" + i);
//            city.setProvince("test_河南省" + i);
//            cityMapper.insert(city);
////            hintManager.close();
//        }
        List<City> courses = cityMapper.selectList(null);
        hintManager.close();
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
