package com.lh.spring.secure.log;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.lh.spring.secure.log.Log)")
    private void cut() {

    }

    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        Object o = null;
        try {
            o = joinPoint.proceed();
            return o;
        } catch (Throwable e) {
            throw e;
        } finally {
            watch.stop();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Log log = method.getAnnotation(Log.class);
            logger.info(String.format("%s, time: %d, args: %s, result: %s ",
                    log.value(),
                    watch.getTime(),
                    ArrayUtils.toString(joinPoint.getArgs()),
                    (o == null ? "null" : o)));
        }
    }
}
