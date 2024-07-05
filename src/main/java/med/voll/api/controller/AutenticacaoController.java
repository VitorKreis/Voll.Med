package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DadosLogin;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.DadosToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){

        var AutheticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        var authetication = manager.authenticate(AutheticationToken);

        var tokenJWT = service.gerarToken((Usuario) authetication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }


}
