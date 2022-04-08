package com.pwx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Administrator
 */
@Data
@TableName("t_city")
public class City {

    private Long id;

    private String name;

    private String province;
}
