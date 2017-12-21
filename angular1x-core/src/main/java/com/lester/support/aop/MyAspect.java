package com.lester.support.aop;

import com.lester.support.util.JsonUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.LinkedList;
import java.util.List;

@Aspect
public class MyAspect {

    private static final Logger log = Logger.getLogger(MyAspect.class);

    @Before("execution(public * tw.com.platform.HPOS.res..*(..))" +
            "|| execution(public * tw.com.platform.task..*(..))" +
            "|| bean(*DAOImpl) && !execution(public * tw.com.platform.RMS.dao.SurroundingAccessLogDAO.*(..))")
    public void before(JoinPoint joinPoint) {
        String shortName = joinPoint.getSignature().toShortString();
        log.info(shortName + " Start, fullName : " + joinPoint.getSignature().toLongString());
        List<String> newArgs = new LinkedList<>();
        for (Object arg : joinPoint.getArgs()) {
            try {
                newArgs.add(JsonUtil.toJson(arg).replace("\\", ""));
            } catch (Throwable t) {
//                log.warn("AOP can't parse param");
//                for (StackTraceElement stackTrace : t.getStackTrace()) {
//                    log.warn(stackTrace);
//                }
                newArgs.add("AOP cant parse param");
            }
        }

//        for (int i = 0 ; i < newArgs.size() ; i++) {
//            log.info(shortName + " param["+ i +"] : " + newArgs.get(i));
//        }

//        for (int i = 0; i < newArgs.size(); i++) {
//            String param = JsonUtil.toJson(newArgs.get(i).replace("\\", ""));
//            String[] paramParts = param.split(",");
//            if (paramParts.length > 2) {
//                int lastJ = 0;
//                StringBuffer temp = new StringBuffer();
//                for (int j = 0 ; j < paramParts.length ; j++) {
//                    temp.append(paramParts[j]);
//                    if (j % 2 == 0) {
//                        log.info(shortName + " param["+ i +"][" + j + "] : " + temp);
//                        temp = new StringBuffer();
//                    }
//                    lastJ = j;
//                }
//                log.info(shortName + " param["+ i +"][" + lastJ + "] : " + temp);
//            } else {
//                log.info(shortName + " param["+ i +"] : " + param);
//            }
//        }

    }

//    @After("execution(public * tw.com.platform.HPOS.res..*(..))" +
//            "|| execution(public * tw.com.platform.task..*(..))" +
//            "|| bean(*DAOImpl)")
//    public void after(JoinPoint joinPoint) {
//        log.info(joinPoint.getSignature().toShortString() + " End");
//    }

//    @Around("execution(public * tw.com.platform.HPOS.res..*(..))")
//    public Response weaveRest(ProceedingJoinPoint joinPoint) {
//        String shortName = joinPoint.getSignature().toShortString();
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            log.info("USER_TOKEN : " + JsonUtil.toJson(request.getAttribute(SysConst.HTTP_ATTR.USER_TOKEN)).replace("\\", ""));
//            Object o = execuArroundWithTime(joinPoint);
//            return (Response) o;
//        } catch (Throwable t) {
//            log.error(shortName + " WS_ERROR");
//            log.error(getErrStackTrace(joinPoint, t));
//            return new RespVO<>(SysConst.RestValid.Error.getCode(), t.toString(), null).extractJsonResp();
//        }
//    }

    @Around("bean(*DAOImpl) && !execution(public * tw.com.platform.RMS.dao.SurroundingAccessLogDAO.*(..))")
    public Object weaveDao(ProceedingJoinPoint joinPoint) throws Throwable {
        return execuArroundWithTime(joinPoint);
    }

//    @Around("execution(public * tw.com.platform.task..*(..))")
//    public Object weaveBatch(ProceedingJoinPoint joinPoint){
//        String shortName = joinPoint.getSignature().toShortString();
//        try {
//            Object result = execuArroundWithTime(joinPoint);
//            if (SysConst.BATCH_NOT_EXECU.equalsIgnoreCase(result.toString())) {
//                return shortName + " SeverStauts 不允許執行，詳情見 log ";
//            }
//            return shortName + " success!";
//        } catch (Throwable t) {
//            StringBuffer sbf = getErrStackTrace(joinPoint, t);
//            log.error(shortName + " BATCH_ERROR");
//            log.error(sbf.toString());
//            return sbf.toString();
//        }
//    }

//    @AfterReturning(value = "execution(public * tw.com.platform.HPOS.res..*(..))" +
//            "|| execution(public * tw.com.platform.task..*(..))" +
//            "|| bean(*DAOImpl) && !execution(public * tw.com.platform.RMS.dao.SurroundingAccessLogDAO.*(..))"
//            , returning = "returnVal")
//    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
//        String shortName = joinPoint.getSignature().toShortString();
//        try {
//            if (returnVal instanceof Response) {
//                log.info(shortName + " result : " + JsonUtil.toJson(((Response) returnVal).getEntity()));
//            } else {
//                log.info(shortName + " result : " + JsonUtil.toJson(returnVal));
//            }
//        } catch (Throwable t) {
//            log.warn("AOP can't parse result");
//        }
//    }

//    @AfterThrowing(value = "within(tw.com.platform.task..*)", throwing = "t")
//    public void batchAfterThrowable(Throwable t) {
//        log.error("exception : " + t.getMessage(), t);
//    }

    private Object execuArroundWithTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getSignature().toShortString() + " End, process use : " + (endTime - startTime) + " sec/1000");
        return result;
    }

    private StringBuffer getErrStackTrace(JoinPoint joinPoint, Throwable t) {
        StringBuffer sbf = new StringBuffer(joinPoint.getSignature().toShortString())
                .append(" Error，exception : ").append(t.toString()).append("\n");
        for (int i = 0; i < 20; i++) {
            sbf.append(t.getStackTrace()[i].toString()).append("\n");
        }
        return sbf;
    }
}
