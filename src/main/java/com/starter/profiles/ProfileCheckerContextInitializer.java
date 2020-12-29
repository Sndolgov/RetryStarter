package com.starter.profiles;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ProfileCheckerContextInitializer implements ApplicationContextInitializer
{
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext)
    {
        if (applicationContext.getEnvironment().getActiveProfiles().length == 0)
            throw new RuntimeException("Profile is mandatory");
    }
}
