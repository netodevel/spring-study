package br.com.netodevel.loggerway.service;

import br.com.netodevel.loggerway.annotation.TrackTime;
import br.com.netodevel.loggerway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @TrackTime
    public void findAll() {
        userRepository.findAll();
    }

}
