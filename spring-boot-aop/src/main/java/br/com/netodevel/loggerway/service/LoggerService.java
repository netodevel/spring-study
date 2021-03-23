package br.com.netodevel.loggerway.service;
import br.com.netodevel.loggerway.domain.Logger;
import br.com.netodevel.loggerway.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerService {

    @Autowired
    private LoggerRepository loggerRepository;

    public Logger save(Logger logger) {
       return loggerRepository.save(logger);
    }

    public List<Logger> findAll() {
      return loggerRepository.findAll();
    }

}
