package Med.Voll.Api_Rest.Infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/login").permitAll();
            request.anyRequest().authenticated();
        });
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEnconded(){
        return new BCryptPasswordEncoder();
    }


}
