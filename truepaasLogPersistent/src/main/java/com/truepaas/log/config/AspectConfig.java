package com.truepaas.log.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mas on 2016/11/7.
 */
@Aspect
@Configuration
public class AspectConfig {
    @Pointcut("execution(* com.truepaas.log.handle.*.get*(..))")
    public void executeService(){}

//    @Around("executeService()")
//    public Object check(ProceedingJoinPoint point) throws Throwable{
//        //访问目标方法的参数：
//        Object[] args = point.getArgs();
//        if (args != null && args.length > 0 && args[0].getClass() == RequestFacade.class) {
//            String author=((HttpServletRequest)args[0]).getHeader("Authorization");
//        }
//        System.out.print("around  execute \n");
//        Object returnValue = point.proceed(args);
//        return returnValue;
//    }
//
//    @Before("executeService()")
//    public void beforeExecute(JoinPoint joinPoint){
//        System.out.print("before execute \n");
//    }
//
//    @AfterReturning("executeService()")
//    public void afterExecute(JoinPoint joinPoint){
//        System.out.print("after execute \n");
//
//    }

}
