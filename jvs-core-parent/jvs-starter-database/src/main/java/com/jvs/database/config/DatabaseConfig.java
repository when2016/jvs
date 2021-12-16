package com.jvs.database.config;

import com.jvs.database.controller.TenantDynamicDatasource;
import com.jvs.database.getter.DefaultDataSourceGetter;
import com.jvs.database.getter.DefaultTableFieldGetter;
import com.jvs.database.getter.IDataSourceGetter;
import com.jvs.database.getter.ITableFieldGetter;
import com.jvs.database.init.DataSourceInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 数据源信息获取配置
 * <p>
 * 用于各个MyBatis拦截器
 *
 * @Author: GuoZi
 */
@Slf4j
public class DatabaseConfig {

    /**
     * 默认的表字段获取类
     */
    @Bean
    @ConditionalOnMissingBean
    public ITableFieldGetter tableFieldGetter() {
        log.info("[mysql-data] 使用默认的表字段获取类: {}", DefaultTableFieldGetter.class.getName());
        return new DefaultTableFieldGetter();
    }

    /**
     * 默认的数据源信息获取类
     * <p>
     * 该默认实现类优先级均低于 jvs-starter-dynamic模块
     * 详情见{@link com.jvs.dynamic.config}
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnMissingClass("com.jvs.dynamic.config.DynamicDataConfig")
    public IDataSourceGetter dataSourceGetter() {
        log.info("[mysql-data] 使用默认的数据源信息获取类: {}", DefaultDataSourceGetter.class.getName());
        return new DefaultDataSourceGetter();
    }

    /**
     * 默认的数据源信息获取类
     * <p>
     * 该默认实现类优先级均低于 jvs-starter-dynamic模块
     * 详情见{@link com.jvs.dynamic.config}
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnMissingClass("com.jvs.dynamic.config.DynamicDataConfig")
    public DataSourceInit dataSourceInit() {
        log.info("[mysql-data] 加载单数据源初始化类: {}", DataSourceInit.class.getName());
        return new DataSourceInit();
    }

    /**
     * 只支持单数据源，不支持多数据源进行分离
     */
    @Bean
    @ConditionalOnMissingBean
    TenantDynamicDatasource mycatController(DataSource dataSource, IDataSourceGetter dataSourceGetter, ITableFieldGetter tableFieldGetter) {
        return new TenantDynamicDatasource(dataSourceGetter, tableFieldGetter, dataSource);
    }

}
