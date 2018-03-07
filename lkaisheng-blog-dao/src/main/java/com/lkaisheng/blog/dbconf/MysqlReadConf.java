package com.lkaisheng.blog.dbconf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 2018/3/7
 *
 * @author: likaisheng
 */

@Configuration
@MapperScan(basePackages = MysqlConf.PACKAGE, sqlSessionTemplateRef = "mysqlReadSqlSessionTemplate", nameGenerator = ReadMapperBeanNameGenerator.class)
public class MysqlReadConf {

    /**
     * 使用DruidDataSource需要指定initMethod，可以在服务启动时检查数据库能否连接上（username, password等）
     * 不然会在第一次查询的时候才初始化数据库连接池
     * 如果不指定initMethod，可以指定spring.datasource.schema属性，并放置查询数据库的初始化sql语句完成初始化操作
     * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer}.
     *
     * @author likaisheng
     * @return dataSourceMysqlRead
     */
    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "mysql.read")
    public DataSource dataSourceMysqlRead(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory mysqlReadSqlSessionFactory(@Qualifier("dataSourceMysqlRead") DataSource dataSource) throws Exception {
        return SqlSessionFactoryConf.injectConf(dataSource, MysqlConf.MAPPER_LOCAITON, MysqlConf.CONFIG_LOCATION);
    }

    @Bean
    SqlSessionTemplate mysqlReadSessionTemplate(@Qualifier("mysqlReadSqlSessionFactory")SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
