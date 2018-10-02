package com.schmalz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.naming.directory.SearchControls;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String SERVICE_USER_ROLE = "SERVICE_USER";

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${security.disable}")
    private boolean securityDisabled;

    @Value("${service.user}")
    private String serviceUser;

    @Value("${service.password}")
    private String servicePassword;



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if (!securityDisabled) {

            inMemoryConfigurer()
                    .withUser(serviceUser)
                        .password(servicePassword)
                        .authorities(SERVICE_USER_ROLE)
                    .and()
                    .configure(auth);
        }
    }

    private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
    inMemoryConfigurer() {
        return new InMemoryUserDetailsManagerConfigurer<>();
    }

    @Configuration
    public static class ApiLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${security.disable}")
        private boolean securityDisabled;


        protected void configure(HttpSecurity http) throws Exception {
            if (!securityDisabled) {
                http.antMatcher("/**").csrf().disable()
                        .formLogin().loginPage("/login").permitAll()
                        .and().exceptionHandling().accessDeniedPage("/403")
                        .and().logout().permitAll()
                        .and()
                        .authorizeRequests()
                        .antMatchers("/css/**", "/webjars/**", "/img/**").permitAll()
                        .and().httpBasic();

            }else{
                http.antMatcher("/**").csrf().disable();
            }
        }
    }
}