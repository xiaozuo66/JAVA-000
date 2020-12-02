package io.kimmking.springboot.config;

import io.kimmking.springboot.context.ReadWriteDataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {

    public ReadWriteRoutingDataSource() {
        ReadWriteDataSourceContext.setDBType("Master");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ReadWriteDataSourceContext.getDBType();
    }
}
