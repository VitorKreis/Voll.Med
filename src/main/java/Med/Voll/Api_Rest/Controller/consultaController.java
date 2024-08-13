package Med.Voll.Api_Rest.Controller;


import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaService;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class consultaController {

    @Autowired
    ConsultaService service;

    @Autowired
    ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criarConsulta(@RequestBody criarConsultaDTO dados){
        service.agendar(dados);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity listaConsultas(){
        var consultas = repository.findAll();

        return ResponseEntity.ok(consultas);
    }


    @GetMapping("/{id}")
    public ResponseEntity listarUmaConsulta(@PathVariable Long id){
        var consultas = repository.getReferenceById(id);

        return ResponseEntity.ok(consultas);
    }


    @Transactional
    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody MotivoCancelamentoDTO motivo){
        service.cancelar(motivo);

        return ResponseEntity.noContent().build();
    }


}
