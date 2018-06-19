package com.atools.swagger.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

/**
 * @author study
 * @since 2018/6/14 0014
 */
@Configuration
@ConditionalOnProperty(name = "spring.swagger.validator-plugin", havingValue = "true", matchIfMissing = true)
@EnableAutoConfiguration
public class MyBeanValidatorPluginsConfiguration extends BeanValidatorPluginsConfiguration {

}
