package com.lkaisheng.blog.dbconf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 2018/3/7
 *
 * @author: likaisheng
 */

@Configuration
@MapperScan(basePackages = MysqlConf.PACKAGE)
public class MysqlConf {

    static final String PACKAGE = "com.lkaisheng.blog.dao.mysql";
    static final String MAPPER_LOCAITON = "classpath:mappers/mysql/*.xml";
    static final String CONFIG_LOCATION = "mybatis-conf.xml";

    /**
     * 使用DruidDataSource需要指定initMethod，可以在服务启动时检查数据库能否连接上（username, password等）
     * 不然会在第一次查询的时候才初始化数据库连接池
     * 如果不指定initMethod，可以指定spring.datasource.schema属性，并放置查询数据库的初始化sql语句完成初始化操作
     * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer}.
     *
     * @author likaisheng
     * @return dataSourceMysqlRead
     */
    @Primary
    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "mysql")
    public DataSource dataSourceMysql(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public SqlSessionFactory mysqlSqlSessionFactory(DataSource dataSource) throws Exception {
        return SqlSessionFactoryConf.injectConf(dataSource,MAPPER_LOCAITON,CONFIG_LOCATION);
    }

    @Primary
    @Bean
    public SqlSessionTemplate mysqlSqlSessionTempleate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean
    public DataSourceTransactionManager mysqlTransactionMapper(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
