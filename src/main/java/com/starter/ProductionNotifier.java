package com.starter;

import javax.annotation.PostConstruct;

public class ProductionNotifier
{
    @PostConstruct
    private void postConstruct(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------IT IS PRODUCTION!!!------------------------");
        System.out.println("----------------------------------------------------------------");
    }

}
