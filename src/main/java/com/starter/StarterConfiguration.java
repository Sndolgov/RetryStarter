package com.starter;

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

}
