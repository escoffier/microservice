package com.authenticationserivce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    //@Autowired
    DataSource dataSource;

    //@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfigurer(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * @Author: Robbie
         * @Description:
         * @param auth
         * @Return: void
         * @Date: 下午1:46 18-11-26
         */
        //super.configure(auth);
        //auth.jdbcAuthentication().dataSource(dataSource);
        auth.inMemoryAuthentication()//.passwordEncoder(passwordEncoder)
                .withUser("john.carnell").password( "password1").roles("USER")
                .and()
                .withUser("william.woodward").password("password2").roles("USER", "ADMIN");

        //System.out.println(auth.getp());
    }

    //https://docs.spring.io/spring-security/site/docs/5.0.2.RELEASE/reference/htmlsingle/#troubleshooting
    //Must explicitly provide the PasswordEncoder in Spring 5.X
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
