package Med.Voll.Api_Rest.Infra.security;

import Med.Voll.Api_Rest.domain.User.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

//    @Value("${api.security.secret}")
//    private String secret;
    private Algorithm algoritmo = Algorithm.HMAC256("12345678");

    public String gerarToken(User user){
        return JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(user.getUsername())
                .withExpiresAt(DataExpiracao())
                .sign(algoritmo);
    }

    public Instant DataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}
