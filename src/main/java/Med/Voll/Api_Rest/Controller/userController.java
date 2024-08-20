package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Infra.security.TokenService;
import Med.Voll.Api_Rest.User.UserService;
import Med.Voll.Api_Rest.domain.User.DadosUser;
import Med.Voll.Api_Rest.domain.User.User;
import Med.Voll.Api_Rest.domain.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/login")
public class userController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosUser dados){

        var user = repository.findByUsername(dados.username());

        var AutheticationToken  = new UsernamePasswordAuthenticationToken(dados.username(), dados.password(),  user.getAuthorities());

        var authetication = authenticationManager.authenticate(AutheticationToken);

        var token = tokenService.gerarToken((User) authetication.getPrincipal());

        return ResponseEntity.ok(token);
    }
}
