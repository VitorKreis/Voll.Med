package Med.Voll.Api_Rest.Infra.security;

import Med.Voll.Api_Rest.domain.User.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuparToken(request);

        if(token != null){

            var subject = tokenService.getSubject(token);

            var usuario = repository.findByUsername(subject);

            var authenticator = new UsernamePasswordAuthenticationToken(usuario,  null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticator);
        }

        filterChain.doFilter(request, response);
    }

    private String recuparToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if(token != null){
            return token.replace("Bearer ", "");
        }

        return null;
    }
}
