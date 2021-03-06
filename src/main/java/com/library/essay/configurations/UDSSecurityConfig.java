package com.library.essay.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Profile("uds-security")
@Configuration
@EnableWebSecurity
public class UDSSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService userDetailsService;

	//1. Configure HTTP URL pattern mappings.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
				.antMatchers("/javax.faces.resource/**", "/pages/public/**", "/pages/logout").permitAll()
				.antMatchers("/pages/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/pages/superUser/**").hasAuthority("ROLE_SUPER")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/pages/public/loginPage.xhtml")
				.and()
                .csrf().disable()
                .logout()
                	.logoutUrl("/pages/logout")
                	.logoutSuccessUrl("/pages/homePage.xhtml")
                	.invalidateHttpSession(true);
	}

	//2. Configure authentication strategies.
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
