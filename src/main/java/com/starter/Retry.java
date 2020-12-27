package com.starter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    int tries() default 1;
    int delay() default 100;
}
