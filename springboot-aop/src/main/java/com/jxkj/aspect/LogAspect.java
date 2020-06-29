package com.jxkj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    /**
     * 第一个 * 表示任意返回值
     * 第二个 * 表示service下的所有类
     * 第三个 * 表示所有方法
     * （..） 表示所有参数
     */
    @Pointcut("execution(* com.jxkj.service.*.*(..))")
    public void pc1(){

    }

    @Before(value = "pc1()")
    public void before(JoinPoint joinPoint){
        // 通过接入点可以获取到方法的名称、修饰符等
        String name = joinPoint.getSignature().getName();
        System.out.println("方法开始执行：" + name);
        logger.info("方法开始执行：" + name);
    }

    @After(value = "pc1()")
    public void after(JoinPoint joinPoint){
        // 通过接入点可以获取到方法的名称、修饰符等
        String name = joinPoint.getSignature().getName();
        System.out.println("方法执行结束：" + name);
        logger.info("方法执行结束：" + name);
    }

    /**
     * result 为返回值的变量名，对应的是方法的参数
     * 参数需要是Object，才可以处理任意类型
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pc1()",returning = "result")
    public void afterRuturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法返回值为：" + result);
        logger.info(name + "方法返回值为：" + result);
    }

    /**
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法抛异常了：" + e.getMessage());
        logger.info(name + "方法抛异常了：" + e.getMessage());
    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return proceedingJoinPoint.proceed();
    }



}
