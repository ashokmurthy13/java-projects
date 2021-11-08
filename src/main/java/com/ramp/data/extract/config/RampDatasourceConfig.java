package com.ramp.data.extract.config;

import com.ramp.data.extract.utils.PasswordUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.ramp.**.dal", sqlSessionFactoryRef = "rampSqlSessionFactory")
public class RampDatasourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RampDatasourceConfig.class);

    @Value("${ramp.db.url}")
    private String url;

    @Value("${ramp.db.user}")
    private String username;

    @Value("${ramp.password:#{null}}")
    private String password;

    @Value("${ramp.db.driver}")
    private String driver;


    @Bean(name = "rampDatasource")
    public DataSource dataSource() {
        password = password != null ? password : PasswordUtils.getPasswordFromFile(username);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        LOG.info("Ramp datasource successfully initialized.");
        return dataSource;
    }

    @Bean(name = "rampTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "rampSqlSessionFactory")
    public SqlSessionFactory sessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource((dataSource()));
        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }
}
