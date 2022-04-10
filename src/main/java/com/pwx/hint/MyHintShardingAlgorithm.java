package com.pwx.hint;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author pwx
 */
@Slf4j
public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    /**
     * 自定义Hint 实现算法
     * 能够保证绕过Sharding-JDBC SQL解析过程
     * @param availableTargetNames -
     * @param shardingValue 不再从SQL 解析中获取值，而是直接通过hintManager.addTableShardingValue("t_city", 1)参数指定
     * @return -
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         HintShardingValue<Long> shardingValue) {
        log.info("shardingValue={}", shardingValue);
        log.info("availableTargetNames={}", availableTargetNames);
        Collection<String> result = new ArrayList<>();
        for (String tableName : availableTargetNames) {
            for (Long value : shardingValue.getValues()) {
                if (tableName.endsWith(String.valueOf(value % 2))) {
                    result.add(tableName);
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
