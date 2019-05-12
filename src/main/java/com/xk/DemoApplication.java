package com.xk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xk.framework.common.Constants;
import com.xk.framework.jpa.reposiotry.base.BaseRepositoryFactoryBean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 示例启动入口
 * @author LV
 */
@EnableJpaRepositories(basePackages = {Constants.DEFAULT_SCAN_PACKAGE},
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@ComponentScan(Constants.DEFAULT_SCAN_PACKAGE)
@EntityScan(Constants.DEFAULT_SCAN_PACKAGE)
@EnableSwagger2
@SpringBootApplication
public class DemoApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DemoApplication.class, args);
    }
}


