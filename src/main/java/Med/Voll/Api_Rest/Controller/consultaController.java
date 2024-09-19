package Med.Voll.Api_Rest.Controller;


import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaService;
import Med.Voll.Api_Rest.domain.Consulta.ListaDadosConsulta;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import Med.Voll.Api_Rest.domain.Consulta.DadosConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class consultaController {

    @Autowired
    ConsultaService service;

    @Autowired
    ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ListaDadosConsulta> criarConsulta(@RequestBody DadosConsulta dados, UriComponentsBuilder uriComponentsBuilder){
        var consulta = service.agendar(dados);

        var uri = uriComponentsBuilder.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ListaDadosConsulta>> listaConsultas(){
        var consultas = repository.findAll().stream().map(ListaDadosConsulta::new).toList();

        return ResponseEntity.ok(consultas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ListaDadosConsulta> listarUmaConsulta(@PathVariable Long id){
        var consultas = repository.getReferenceById(id);

        return ResponseEntity.ok(new ListaDadosConsulta(consultas));
    }


    @Transactional
    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody MotivoCancelamentoDTO motivo){
        service.cancelar(motivo);

        return ResponseEntity.noContent().build();
    }


}
