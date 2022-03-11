package com.ivoronline.springboot_security_rememberme_db_h2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //PROPERTIES
  @Autowired UserDetailsService        userDetailsService;
  @Autowired PersistentTokenRepository persistentTokenRepository;

  //=================================================================
  // PERSISTENT TOKEN REPOSITORY
  //=================================================================
  @Bean
  public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
                            tokenRepository.setDataSource(dataSource);
    return tokenRepository;
  }

  //=================================================================
  // CONFIGURE
  //=================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ENABLE REMEMBER ME COOKIE
    httpSecurity.rememberMe()
      .tokenRepository(persistentTokenRepository)
      .userDetailsService(userDetailsService);

    //H2 CONSOLE
    httpSecurity.authorizeRequests(authorize -> { authorize.antMatchers("/h2-console/**").permitAll(); });
    httpSecurity.headers().frameOptions().sameOrigin();

    //DISABLE CSRF
    httpSecurity.csrf().disable();

    //SECURE ALL RESOURCES
    httpSecurity.authorizeRequests().anyRequest().authenticated();

    //DEFAULT LOGIN FORM
    httpSecurity.formLogin();

  }

}
