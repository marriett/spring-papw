package br.unipe.pos.mobile.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SegurancaConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.antMatchers("/css/**", "/index").permitAll()
	.antMatchers("/user/**").hasRole("USER")
	.antMatchers("/user/**").hasRole("admin")
	.antMatchers("/admin/**").hasRole("admin")
	
	.antMatchers("/cliente/**").hasRole("USER")
	.antMatchers("/admin/**").hasRole("admin")
	
	.and().formLogin();

	// .loginPage("/login").failureUrl("/login-error");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	.withUser("user").password("123").roles("USER")
	.and()
	.withUser("fuji").password("123").roles("admin")
	.and()
	.withUser("mbk").password("321").roles("admin");

	}
}
