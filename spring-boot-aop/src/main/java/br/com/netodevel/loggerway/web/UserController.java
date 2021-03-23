package br.com.netodevel.loggerway.web;

import br.com.netodevel.loggerway.annotation.TrackTime;
import br.com.netodevel.loggerway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @TrackTime
    @GetMapping("/users")
    public String index() {
        userService.findAll();
        return "hello aop";
    }

}
