package com.vinninho.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    //log all requests like [METHOD] [URL] [IP]
    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void logRequest(JoinPoint jp){
        log.info("Request received " + jp.getSignature() );
    }
}
