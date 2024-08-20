package Med.Voll.Api_Rest.Infra.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
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
        http.exceptionHandling(execepiton -> execepiton.accessDeniedHandler(accessDeniedHandler()));

        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/login").permitAll();
            // "/medico" Authority for post, put and delete
            request.requestMatchers(HttpMethod.POST, "/medico/**").hasAnyAuthority("MEDICO", "GERENTE");
            request.requestMatchers(HttpMethod.PUT, "/medico/**").hasAnyAuthority("MEDICO", "GERENTE");
            request.requestMatchers(HttpMethod.DELETE, "/medico/**").hasAuthority("GERENTE");
            // "/paciente" Authority for post, put and delete
            request.requestMatchers(HttpMethod.POST, "/paciente/**").hasAnyAuthority("MEDICO", "GERENTE", "ENFERMEIRA");
            request.requestMatchers(HttpMethod.PUT, "/paciente/**").hasAnyAuthority("MEDICO", "GERENTE", "ENFERMEIRA");
            request.requestMatchers(HttpMethod.DELETE, "/paciente/**").hasAuthority("GERENTE");
            // "/consultas" Authority for post, put and delete
            request.requestMatchers(HttpMethod.POST, "/consultas").hasAnyAuthority("MEDICO", "GERENTE", "ENFERMEIRA");
            request.requestMatchers(HttpMethod.PUT, "/consultas").hasAuthority("GERENTE");
            request.requestMatchers(HttpMethod.DELETE, "/consultas").hasAuthority("GERENTE");

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


    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.addHeader("Acesso inválido", "Acesso inválido");
            response.getWriter().write("Acesso invalido");
            response.getWriter().flush();
        };
    }


}
