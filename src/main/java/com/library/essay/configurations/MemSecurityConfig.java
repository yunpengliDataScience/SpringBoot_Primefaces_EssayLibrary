package com.library.essay.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("mem-security")
@Configuration
@EnableWebSecurity
public class MemSecurityConfig extends WebSecurityConfigurerAdapter {

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
		auth.inMemoryAuthentication()
			.withUser("mem").password("{noop}mem").roles("ADMIN", "SUPER", "USER");
		
		auth.inMemoryAuthentication()
			.withUser("mem2").password("{noop}mem2").roles("SUPER");
	}
}
