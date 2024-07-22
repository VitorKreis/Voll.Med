package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Medico.criarMedicoDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medico")
public class medicoController {


    @PostMapping
    public void criarMedico(@RequestBody  @Valid criarMedicoDTO dados) {
        System.out.println(dados);
    }


}
