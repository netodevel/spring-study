package br.com.netodevel.loggerway.web;

import br.com.netodevel.loggerway.domain.Logger;
import br.com.netodevel.loggerway.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    @GetMapping("/loggers")
    private ResponseEntity<List<Logger>> index() {
        return new ResponseEntity<>(loggerService.findAll(), HttpStatus.OK);
    }

}
