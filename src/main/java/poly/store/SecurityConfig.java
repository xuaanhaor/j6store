package poly.store;

import lombok.SneakyThrows;
import poly.store.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import poly.store.service.AccountService;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AccountService accountService;
    @Autowired
    @Lazy
    BCryptPasswordEncoder pe;

    // Cung cap nguon du lieu dang nhap
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            try {
                Account user = accountService.findById(username);
                String password = pe.encode(user.getPassword());
                String[] roles = user.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + "not found");
            }
        };
    }

    // Phan quyen su dung
    @SneakyThrows
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("STAFF", "DIRE")
                .antMatchers("/rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll();
//http.httpBasic();
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false)
                .failureUrl("/security/login/error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().rememberMe().rememberMeParameter("remember");

        http.rememberMe().tokenValiditySeconds(86400);

        http.exceptionHandling().accessDeniedPage("/security/unauthorized");

        http.logout().logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");
        return http.build();
    }


    // Co che ma hoa mat khau
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cho phep truy xuat REST API tu ben ngoai(domain khac)bv
    @CrossOrigin("*")
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
