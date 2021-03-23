package br.com.netodevel.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.netodevel.authserver.service.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void configure (AuthenticationManagerBuilder
			authenticationManagerBuilder)throws Exception{
		authenticationManagerBuilder.userDetailsService(userDetailService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity security)throws Exception{
		security.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
		.antMatchers(HttpMethod.GET, "/public/**");
	}
	//
	//	@Override
	//	@Bean
	//	public AuthenticationManager authenticationManagerBean() throws Exception {
	//		return super.authenticationManagerBean();
	//	}
	//
	//	@Override
	//	@Bean
	//	public UserDetailsService userDetailsServiceBean() throws Exception {
	//		return super.userDetailsServiceBean();
	//	}
	//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(userDetailsService);
//		}

}
