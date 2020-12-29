package com.starter.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileDetectorEPP implements EnvironmentPostProcessor
{
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application)
    {
        if (environment.getActiveProfiles().length == 0)
            environment.setActiveProfiles(isDevOS()? SpringProfiles.DEV : SpringProfiles.PROD);
    }

    private boolean isDevOS()
    {
        String nameOS = System.getProperty("os.name").toLowerCase();

        return nameOS.contains("mac os") || nameOS.contains("windows");
    }

}
