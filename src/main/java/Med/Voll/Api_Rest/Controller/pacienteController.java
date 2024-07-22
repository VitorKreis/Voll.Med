package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Paciente.criarPacienteDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class pacienteController {

    @PostMapping()
    public void criarPaciente(@RequestBody  @Valid criarPacienteDTO dados){
        System.out.println(dados);
    }

}
