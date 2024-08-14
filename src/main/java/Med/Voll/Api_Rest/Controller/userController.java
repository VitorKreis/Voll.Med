package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.User.DadosUser;
import Med.Voll.Api_Rest.domain.User.User;
import Med.Voll.Api_Rest.domain.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class userController {

    @Autowired
    UserRepository repository;

    @PostMapping
    @Transactional
    public void login(@RequestBody DadosUser dados){
        var user = new User(dados);

        repository.save(user);
    }
}
