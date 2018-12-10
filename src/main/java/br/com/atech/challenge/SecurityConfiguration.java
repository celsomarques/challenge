package br.com.atech.challenge;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.atech.challenge.security.JwtAuthenticationFilter;
import br.com.atech.challenge.security.JwtLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
          .inMemoryAuthentication()
          .withUser("challenge")
            .password(encoder.encode("challenge"))
            .roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter("/login", authenticationManager());
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}