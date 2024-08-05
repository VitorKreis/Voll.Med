package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class medicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<cadastroMedicoResponse> criarMedico(@RequestBody  @Valid criarMedicoDTO dados, UriComponentsBuilder uriComponentsBuilder) {

        var medico = new Medico(dados);

        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new cadastroMedicoResponse(medico));
    }

    @GetMapping
    public ResponseEntity<List<listarMedicosDTO>> listarMedicos() {
        var pacientes = repository.findByAtivoTrue().stream().map(listarMedicosDTO::new).toList();
        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<listarMedicosDTO> listarUmMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new listarMedicosDTO(medico));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid atualizarMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarMedico(dados);

        return ResponseEntity.ok(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativarCadastro();

       return ResponseEntity.noContent().build();
    }



}
