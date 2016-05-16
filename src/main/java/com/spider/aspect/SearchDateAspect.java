package com.spider.aspect;

import com.spider.utils.Calendars;
import com.spider.utils.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2016/3/10-11:06.
 *
 * @author ronnie
 */
@Aspect
@Configuration
public class SearchDateAspect {

    /**
     * 拦截所有Controller下的所有list*方法，这些方法都是根据开始时间和结束时间返回列表数据
     *
     * @param startDate
     * @param endDate
     */
    @Pointcut("execution(* com.spider.manager.controller.*Controller.list*(java.util.Date,java.util.Date)) && args(startDate,endDate)")
    private void searchDatePointcut(Date startDate, Date endDate) {

    }

    /**
     * 由于业务需要，比赛以今天11点到第二天11点编今天的号，我们通过AOP拦截，统一处理一下
     *
     * @param joinpoint
     * @param startDate
     * @param endDate
     * @return
     * @throws Throwable
     */
    @Around(value = "searchDatePointcut(startDate,endDate)", argNames = "startDate, endDate")
    public Object dealSearchDate(ProceedingJoinPoint joinpoint, Date startDate, Date endDate) throws Throwable {

        Object[] args = joinpoint.getArgs();
        if (args[0] == null) {
            args[0] = Calendars.getTodayEleven();
            args[1] = DateUtils.add(new Date(), 7, TimeUnit.DAYS);//默认显示今天及以后的所有赔率
        } else {
            args[0] = DateUtils.addHours(startDate, 11);
            args[1] = DateUtils.addHours(endDate, 11);
        }
        return joinpoint.proceed(args);
    }
}
