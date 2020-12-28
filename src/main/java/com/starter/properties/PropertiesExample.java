package com.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "info")
public class PropertiesExample
{
    private List<String> previousVersions;
}
