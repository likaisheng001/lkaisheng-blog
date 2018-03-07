package com.lkaisheng.blog.dbconf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 2018/3/7
 * 配置SqlSessionFactory的通用代码
 * @author: likaisheng
 */

public class SqlSessionFactoryConf {
    public static SqlSessionFactory injectConf(DataSource dataSource, String mapperLocation, String configLocation) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
        bean.setMapperLocations(resources);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        return bean.getObject();
    }
}
