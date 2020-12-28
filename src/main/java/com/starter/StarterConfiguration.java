package com.starter;

import com.starter.conditions.ConditionalOnProduction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    public ProductionNotifier getProductionNotifier(){
        return new ProductionNotifier();
    }

}
