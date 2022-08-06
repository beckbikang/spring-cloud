package cn.beckbi.algo;


import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;


public class OrderSingleKeyTableShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    /**
     *
     *
     * @param tableNames Collection<String>
     * @param shardingValue PreciseShardingValue<Long>
     * @return String
     */
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> shardingValue) {
        String targetTableName = "order_" + (shardingValue.getValue()%tableNames.size());
        for (String tableName : tableNames) {
            if (tableName.equals(targetTableName)) {
                return tableName;
            }
        }
        //此处应该抛出异常
        return "";
    }



    @Override
    public Collection<String> doSharding(Collection<String> availableTableNames, RangeShardingValue<Long> shardingValue) {
        return availableTableNames;
    }

    @Override
    public Properties getProps(){
        return null;
    }

    @Override
    public void init(Properties props) {

    }

    @Override
    public String getType() {
        return "CLASS_BASED";
    }

}
