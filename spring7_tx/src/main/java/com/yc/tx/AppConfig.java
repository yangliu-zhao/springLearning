package com.yc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 连接池
 */
@Configuration
@ComponentScan(basePackages = {"com.yc"})
public class AppConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        //数据源   数据库连接池
        DataSource ds = new ComboPooledDataSource();
        ((ComboPooledDataSource) ds).setDriverClass("com.mysql.jdbc.Driver");
        //
        ((ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/testBank?serverTimezone=GMT%2B8");
        ((ComboPooledDataSource) ds).setUser("root");
        ((ComboPooledDataSource) ds).setPassword("root");
        return ds;
    }
}
