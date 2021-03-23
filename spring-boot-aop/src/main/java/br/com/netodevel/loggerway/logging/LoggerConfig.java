package br.com.netodevel.loggerway.logging;

import br.com.netodevel.loggerway.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    @Autowired
    private LoggerService loggerService;

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect(loggerService);
    }

}
