package com.lester.support.aop;

import com.lester.support.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class MyAspect {

    /**
     * 前置通知
     */
//    @Before("execution(* com.lester.core.service.IAopManService.printName(..))")
    public void before() {
        System.out.println("前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
//    @AfterReturning(value="execution(* com.lester.core.service.IAopManService.printName(..))",returning = "returnVa")
    public void AfterReturning(Object returnVa) {
        System.out.println("后置通知...." + returnVa);
    }


    /**
     * 环绕通知
     *
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("within(com.lester.core.service..*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            System.out.println("Around Start");

            System.out.println("Around：at = " + joinPoint.getTarget().getClass());

            System.out.println("Around：calling method = " + joinPoint.getSignature().toLongString());

            System.out.println("Around：param = " + Arrays.toString(joinPoint.getArgs()));

            Object obj = joinPoint.proceed();
            System.out.println("Around：result = " + JsonUtil.toJson(obj));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("Around End");
        }

    }

    /**
     * 抛出通知
     *
     * @param e
     */
//    @AfterThrowing(value="execution(* com.lester.core.service.IAopManService.printName(..))",throwing = "e")
    public void afterThrowable(Throwable e) {
        System.out.println("出现异常:msg=" + e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
//    @After(value="execution(* com.lester.core.service.IAopManService.printName(..))")
    public void after() {
        System.out.println("最终通知....");
    }
}
