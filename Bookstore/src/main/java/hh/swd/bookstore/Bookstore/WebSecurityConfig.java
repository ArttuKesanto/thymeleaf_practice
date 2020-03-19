package hh.swd.bookstore.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import hh.swd.bookstore.Bookstore.webcontrollers.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // Periytyy valmiista luokasta.
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out. Decide yourself.
        .and()
        .authorizeRequests().antMatchers("/signup", "/saveuser", "/allbooks", "/allcats").permitAll() //saveuser tallentaa userin, ei ADMINia.
        .and()
        .authorizeRequests().antMatchers("/delete{id}", "/newbook", "/newcat", "/save", "/saveedit", "/savecat", "/saveeditcat", "/deletebook/{id}", "/deletecat/{categoryId}", "/editbook/{id}", "/editcat/{categoryId}").hasAuthority("ADMIN")
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .authorizeRequests().antMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
        .and()
      .formLogin()
          //.loginPage("/login") Not needed - using the module's own template, just in case.
          .defaultSuccessUrl("/allbooks")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
   /* @Bean Ei tätä, koska suoraan tietokantaan.
   // @Override
   // public UserDetailsService userDetailsService() {
   //     List<UserDetails> users = new ArrayList();
   //	UserDetails user = User.withDefaultPasswordEncoder()
   //             .username("user")
   //             .password("password")
   //             .roles("USER")
   //             .build();
   //
   // 	users.add(user);
   // 	
   // 	user = User.withDefaultPasswordEncoder()
   //                .username("admin")
                   .password("password")
                   .roles("USER", "ADMIN")
                   .build();
    	
    	users.add(user);
    	
        return new InMemoryUserDetailsManager(users);
    } */
}
