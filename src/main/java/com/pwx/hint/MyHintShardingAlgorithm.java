package com.pwx.hint;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName: MyHintShardingAlgorithm
 * @Description: Hint使用场景：
 * 1、数据分片操作，如果分片键没有在SQL或数据表中，而是在业务逻辑代码中
 * 2、读写分离操作，如果强制在主库进行某些数据操作
 * @Author: qjc
 * @Date: 2021/11/10 6:17 下午
 */
@Slf4j
public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            log.info("each:{}", each);
            for (Long value : shardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "HINT";
    }
}
