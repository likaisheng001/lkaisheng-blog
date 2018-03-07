package com.lkaisheng.blog.dbconf;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

/**
 * 2018/3/7
 *
 * @author: likaisheng
 */

public class ReadMapperBeanNameGenerator implements BeanNameGenerator{

    private static final String PREFIX = "read";

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        String shortClassName = ClassUtils.getShortName(beanDefinition.getBeanClassName());
        return PREFIX + shortClassName;
    }

}
