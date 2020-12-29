package com.starter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class RetryAOP
{
    @Around("@annotation(com.starter.Retry)")
    public Object aroundRetry(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Retry retryAnnotation = method.getAnnotation(Retry.class);
        int tries = retryAnnotation.tries();
        int delay = retryAnnotation.delay();
        int currentTry = 0;
        Throwable throwable = new Throwable("tries <= 0");
        while (currentTry < tries)
        {
            System.out.println("Attempt number: " + (currentTry + 1));
            try
            {
                return proceedingJoinPoint.proceed();
            } catch (Throwable th)
            {
                throwable = th;
                currentTry++;
                try
                {
                    if (currentTry < tries)
                        Thread.sleep(delay);
                } catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }
        }
        throw throwable;
    }

}
