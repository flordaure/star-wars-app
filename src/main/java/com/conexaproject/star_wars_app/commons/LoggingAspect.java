package com.conexaproject.star_wars_app.commons;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.conexaproject.star_wars_app..*) &&" +
            "!within(com.conexaproject.star_wars_app.jwtconfig..*) && " +
            "!within(com.conexaproject.star_wars_app.config..*)")
    public void logForAll(){}

    @Before("logForAll()")
    public void logBeforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();

        Object[]args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg: args) {
            if(params.length()>0){
                params.append(", ");
            }
            params.append(arg != null ? arg.toString() : "null");
        }

        LOG.info("START OPERATION: {}. Parameters sent: [{}]", methodName, params.toString());
    }

    @After("logForAll()")
    public void logAfterMethod(JoinPoint joinPoint){
        LOG.info("END OPERATION: {}.", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "logForAll() && !execution(void *.*(..))", returning = "result")
    public void logMethodResult(JoinPoint joinPoint, Object result){
        LOG.info("{} method executed successfully. Result: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "logForAll()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        String methodName = joinPoint.getSignature().getName();

        LOG.error("The '{}' method threw the exception: {}", methodName, exception.getMessage());
    }
}
