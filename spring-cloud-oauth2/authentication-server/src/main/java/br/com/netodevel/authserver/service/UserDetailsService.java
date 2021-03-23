package br.com.netodevel.authserver.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.netodevel.authserver.model.Users;
import br.com.netodevel.authserver.repository.UserRepository;

@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getGrantedAuthorities());
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(){
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		grantedAuthorities.add(grantedAuthority);
		return grantedAuthorities;
	}
}