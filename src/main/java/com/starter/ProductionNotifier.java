package com.starter;

import com.starter.properties.PropertiesExample;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class ProductionNotifier
{
    @Autowired
    private PropertiesExample properties;

    @PostConstruct
    private void postConstruct(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------IT IS PRODUCTION!!!------------------------");
        if (properties.getPreviousVersions() != null && !properties.getPreviousVersions().isEmpty()){
            System.out.println("PREVIOUS VERSIONS: ");
            properties.getPreviousVersions().forEach(v -> System.out.print(v + " / "));
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------");
    }

}
