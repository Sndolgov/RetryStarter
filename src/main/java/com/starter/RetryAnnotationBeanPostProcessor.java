package com.starter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.*;
import java.util.*;

public class RetryAnnotationBeanPostProcessor implements BeanPostProcessor {
    private Map<String, List<ValueRetryAnnotation>> beansMethodsAnnotatedRetry = new HashMap<>();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        for (Method method : beanClass.getDeclaredMethods()) {
            Retry annotation = method.getAnnotation(Retry.class);
            if (annotation != null) {
                if (beansMethodsAnnotatedRetry.get(beanName) == null) {
                    beansMethodsAnnotatedRetry.put(beanName, Collections.singletonList(new ValueRetryAnnotation(method.getName(), annotation.tries(), annotation.delay())));
                } else {
                    beansMethodsAnnotatedRetry.get(beanName).add(new ValueRetryAnnotation(method.getName(), annotation.tries(), annotation.delay()));
                }

            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<ValueRetryAnnotation> valueRetryAnnotations = beansMethodsAnnotatedRetry.get(beanName);
        if (valueRetryAnnotations != null) {
            Class<?> beanClass = bean.getClass();
            for (Method method : beanClass.getDeclaredMethods()) {
                ValueRetryAnnotation valueRetryAnnotation = getValueByMethodName(valueRetryAnnotations, method.getName());
                if (valueRetryAnnotation != null) {
                    return changeMethod(beanClass, method, valueRetryAnnotation, bean);
                }
            }
        }
        return bean;
    }

    private Object changeMethod(Class<?> beanClass, Method method, ValueRetryAnnotation valueRetryAnnotation, Object bean)
    {
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, methodProxy, args) -> {
            int tries = 0;
            Exception exception = null;
            while (tries < valueRetryAnnotation.tries) {
                try {
                    return method.invoke(bean, args);
                } catch (Exception e) {
                    try {
                        Thread.sleep(valueRetryAnnotation.delay);
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    exception = e;
                    tries++;
                }
            }
            throw exception;
        });
    }

    private ValueRetryAnnotation getValueByMethodName(List<ValueRetryAnnotation> valueRetryAnnotations, String name) {
        for (ValueRetryAnnotation value : valueRetryAnnotations) {
            if (value.methodName.equals(name))
                return value;
        }
        return null;
    }


    private static class ValueRetryAnnotation {
        String methodName;
        int tries;
        int delay;

        ValueRetryAnnotation(String methodName, int tries, int delay) {
            this.methodName = methodName;
            this.tries = tries;
            this.delay = delay;
        }

    }


}
