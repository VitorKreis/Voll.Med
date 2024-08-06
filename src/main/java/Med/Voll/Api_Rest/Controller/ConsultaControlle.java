package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Consulta.Consulta;
import Med.Voll.Api_Rest.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.Consulta.ConsultaService;
import Med.Voll.Api_Rest.Consulta.criarConsultaDTO;
import Med.Voll.Api_Rest.Medico.MedicoRepository;
import Med.Voll.Api_Rest.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaControlle {

    @Autowired
    ConsultaService service;


    @PostMapping
    @Transactional
    public void criarConsulta(@RequestBody criarConsultaDTO dados){
        service.agendar(dados);
    }

}
