package by.intexsoft.vihrova.votingsystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/dishes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dishes").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/dishes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/dishes/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/menus/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/menus").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/menus/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/menus/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/menus/{menuId}/dishes").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/menus/{menuId}/dishes").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/restaurants/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/restaurants").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/restaurants/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/restaurants/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/restaurant/{restaurantId}/menus").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/restaurant/{restaurantId}/menus").hasRole("USER")

                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
