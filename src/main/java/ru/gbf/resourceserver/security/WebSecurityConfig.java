package ru.gbf.resourceserver.security;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.types.UserRole;

@Deprecated
@Component
//@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
   // private final JWTAuthenticationFilter jwtAuthenticationFilter;

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/stockpile/**").hasAnyAuthority(UserRole.ADMIN.name(), UserRole.USER.name())
                .antMatchers(HttpMethod.GET, "/api/goods/**").hasAnyAuthority(UserRole.USER.name())
                .antMatchers(HttpMethod.POST, "/api/goods/**").hasAnyAuthority(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/goods/**").hasAnyAuthority(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/goods/**").hasAnyAuthority(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/categories/**").hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
            //.anyRequest().authenticated()
            .and()
            .httpBasic().disable();
    }*/

}
