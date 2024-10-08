package Med.Voll.Api_Rest.Controller;


import Med.Voll.Api_Rest.domain.Medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class medicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRespostaMedico> criarMedico(@RequestBody  @Valid DadosMedico dados, UriComponentsBuilder uriComponentsBuilder) {

        var medico = new Medico(dados);

        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosRespostaMedico(medico));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MEDICO')")
    public ResponseEntity<Page<ListaDadosMedico>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var pacientes = repository.findByAtivoTrue(pageable).map(ListaDadosMedico::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaDadosMedico> listarUmMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new ListaDadosMedico(medico));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dados, @PathVariable Long id){
        var medico = repository.getReferenceById(id);
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
