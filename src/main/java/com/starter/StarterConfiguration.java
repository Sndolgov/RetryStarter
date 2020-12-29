package com.starter;

import com.starter.conditions.CompositeConditionalOnProduction;
import com.starter.conditions.ConditionalOnProduction;
import com.starter.properties.PropertiesExample;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PropertiesExample.class})
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
    // @ConditionalOnProperty is not repeatable annotation
//    @ConditionalOnProperty("info.previous-versions")
    @CompositeConditionalOnProduction
    @ConditionalOnMissingBean(name = "informationIntroducer")
    @ConditionalOnProduction
//    @ConditionalOnMissingBean(InformationIntroducer.class)
    public InformationIntroducer getProductionNotifier(){
        return new InformationIntroducer();
    }

}
