package Med.Voll.Api_Rest.Controller;


import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaService;
import Med.Voll.Api_Rest.domain.Consulta.ListaConsultaDto;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class consultaController {

    @Autowired
    ConsultaService service;

    @Autowired
    ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ListaConsultaDto> criarConsulta(@RequestBody criarConsultaDTO dados, UriComponentsBuilder uriComponentsBuilder){
        var consulta = service.agendar(dados);

        var uri = uriComponentsBuilder.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ListaConsultaDto>> listaConsultas(){
        var consultas = repository.findAll().stream().map(ListaConsultaDto::new).toList();

        return ResponseEntity.ok(consultas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ListaConsultaDto> listarUmaConsulta(@PathVariable Long id){
        var consultas = repository.getReferenceById(id);

        return ResponseEntity.ok(new ListaConsultaDto(consultas));
    }


    @Transactional
    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody MotivoCancelamentoDTO motivo){
        service.cancelar(motivo);

        return ResponseEntity.noContent().build();
    }


}
