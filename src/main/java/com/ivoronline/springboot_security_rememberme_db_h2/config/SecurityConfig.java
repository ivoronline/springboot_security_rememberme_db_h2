package com.ivoronline.springboot_security_rememberme_db_h2.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private final UserDetailsService        userDetailsService;
  private final PersistentTokenRepository persistentTokenRepository ;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ENABLE REMEMBER ME COOKIE
    httpSecurity.rememberMe()
      .tokenRepository(persistentTokenRepository).userDetailsService(userDetailsService);

    //H2 CONSOLE
    httpSecurity.authorizeRequests(authorize -> { authorize.antMatchers("/h2-console/**").permitAll(); });
    httpSecurity.headers().frameOptions().sameOrigin();

    //DISABLE CSRF
    httpSecurity.csrf().disable();

    //SECURE EVERYTHING
    httpSecurity.authorizeRequests().anyRequest().authenticated();

    //DEfAULT LOGIN FORM
    httpSecurity.formLogin();

  }

}
