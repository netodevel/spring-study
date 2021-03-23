package br.com.netodevel.authserver.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.netodevel.authserver.model.Users;

public class MyUserDetails extends Users implements UserDetails{

	private static final long serialVersionUID = -9039764919893700467L;

	public MyUserDetails(Users usuario) {
        super(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	for(int i = 0; i < super.getProfiles().size(); i ++) {
    		System.out.println("profile: " + getProfiles().get(i));
    	}
        return super.getProfiles();
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
