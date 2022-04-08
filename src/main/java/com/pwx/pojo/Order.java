package com.pwx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author Administrator
 */
@Data
@TableName("t_order")
public class Order {

    private Long id;

    private String orderNo;

    private Long userId;

    private Double price;

    private Date createTime;

    private Date updateTime;
}
