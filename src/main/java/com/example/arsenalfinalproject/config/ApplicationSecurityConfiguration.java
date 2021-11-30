package com.example.arsenalfinalproject.config;

import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //access to all static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // allows access to the home page , login , registration , prodcut all , news details
               .antMatchers("/" , "/users/login" , "/users/register" , "/news/details/{id}" , "/product/all" , "/game/quest" ,"/page/{i}" , "/api/{newsId}/comments" ).permitAll()
                .antMatchers("/statistics" , "/product/add" , "/admin/adminchangeprofile" , "/product/{id}/edit").hasRole(RoleNameEnum.ADMIN.name())
                //all other pages for unauthnticated users
                .antMatchers("/**").authenticated()

                //Config Login
                .and()
                //configure login with login
                .formLogin()
                //login page is location at htttp://server:<port>/users/login
                .loginPage("/users/login")
                //
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")

                //Config Logout
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                //remove the session from server
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }
}
