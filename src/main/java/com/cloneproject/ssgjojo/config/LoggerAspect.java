package com.cloneproject.ssgjojo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
	
	@Around("execution(* com.cloneproject.ssgjojo..controller.*Controller.*(..)) || execution(* com.cloneproject.ssgjojo..service.*Imple.*(..)) || execution(* com.cloneproject.ssgjojo..repository.*Repository.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		if (name.indexOf("Controller") > -1) {
			type = "Controller  \t:  ";
		}
		else if (name.indexOf("Service") > -1) {
			type = "ServiceImple  \t:  ";
		}
		else if (name.indexOf("Repository") > -1) {
			type = "Repository  \t\t:  ";
		}
		log.warn(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}
