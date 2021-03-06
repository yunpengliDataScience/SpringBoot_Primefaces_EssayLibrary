package com.library.essay.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("ldap-security")
@Configuration
@EnableWebSecurity
public class LDAPSecurityConfig extends WebSecurityConfigurerAdapter {

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
		 
		auth.ldapAuthentication()
         	.userDnPatterns("uid={0},ou=people")  //authentication search
         	.groupSearchBase("ou=groups")		//authority search
         	.groupSearchFilter("(member={0})")
         	.rolePrefix("ROLE_")
         	.contextSource()  //ldap server info 
         	.root("dc=essay,dc=library,dc=com")
            .ldif("classpath:users.ldif");
            //.url("ldap://localhost:33389/dc=essay,dc=library,dc=com");  //for a non-embedded LDAP server
	}

}
