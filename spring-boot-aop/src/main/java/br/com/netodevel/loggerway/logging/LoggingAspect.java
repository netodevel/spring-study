package br.com.netodevel.loggerway.logging;

import br.com.netodevel.loggerway.service.LoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LoggerService loggerService;

    private Timer timer;

    public LoggingAspect(LoggerService loggerService) {
        this.loggerService = loggerService;
    }
//
//    @Before("execution(* br.com.netodevel.loggerway.web.*.*(..))")
//    public void before(JoinPoint joinPoint){
//        timer = new Timer();
//        logger.info("Iniciou execução: {}", joinPoint);
//    }
//
//    @After("execution(* br.com.netodevel.loggerway.web.*.*(..))")
//    public void after(JoinPoint joinPoint) {
//        logger.info("Terminou a execução: {}", joinPoint);
//        logger.info("Tempo: " + timer);
//    }

    @Around("@annotation(br.com.netodevel.loggerway.annotation.TrackTime)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        timer = new Timer();
        joinPoint.proceed();
        logger.info("Time Taken by {} is {}", joinPoint, timer);
        this.loggerService.save(new br.com.netodevel.loggerway.domain.Logger(joinPoint.toShortString(), timer.toString()));
    }

}