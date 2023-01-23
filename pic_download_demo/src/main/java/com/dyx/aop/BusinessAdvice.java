package com.dyx.aop;

import com.dyx.model.pojo.ResponsePojo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

/**
 * @author Davison
 * @description BusinessAdvice
 * @date 2023/1/23 13:36
 */
@Aspect
@Order(value = 99)
@Component
public class BusinessAdvice {

    @Pointcut(value = "execution(* com.dyx.controller..*.*(..))")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public ResponsePojo around(ProceedingJoinPoint pjp) {
        ResponsePojo response = new ResponsePojo();
        try {
            Object proceed = pjp.proceed();
            response.setData(proceed);
            response.setCode(0);
        }catch (Throwable e) {
            if (e instanceof ValidationException) {
                response.setCode(-1);
                response.setMessage(e.getMessage());
            } else {
                response.setCode(-1);
                response.setMessage(e.getMessage());
            }
        }
        return response;
    }
}
