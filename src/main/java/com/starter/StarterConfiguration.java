package com.starter;

import com.starter.conditions.ConditionalOnProduction;
import com.starter.properties.PropertiesExample;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PropertiesExample.class)
public class StarterConfiguration {

  /*  @Bean
    public BeanPostProcessor getExceptionsHandlerAnnotationBeanPostProcessor() {
        return new RetryAnnotationBeanPostProcessor();
    }*/

    @Bean
    public RetryAOP getRetryAOP() {
        return new RetryAOP();
    }

    @Bean
    @ConditionalOnProduction
    @ConditionalOnProperty("info.previous-versions")
    public ProductionNotifier getProductionNotifier(){
        return new ProductionNotifier();
    }

}
