package br.com.netodevel.loggerway.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String method;
    private String time;

    public Logger(){}

    public Logger(String method, String time) {
        this.method = method;
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
