package br.com.netodevel.authserver.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.netodevel.authserver.model.Profile;
import br.com.netodevel.authserver.model.Users;
import br.com.netodevel.authserver.repository.ProfileRepository;
import br.com.netodevel.authserver.repository.UserRepository;

@Component
public class CargaInicial  implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    UserRepository usuarioRepository;
    
    @Autowired
    ProfileRepository perfilRepository;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        List<Profile> perfis = perfilRepository.findAll();
        
        if(perfis.isEmpty()){
        	// referência de um curso.
        	Users users = usuarioRepository.save(new Users("netodevel", "admin", "123"));
        	perfilRepository.save(new Profile("ROLE_ADMIN", users));
        }
    }
    
}
