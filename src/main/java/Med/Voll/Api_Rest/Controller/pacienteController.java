package Med.Voll.Api_Rest.Controller;


import Med.Voll.Api_Rest.domain.Paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
public class pacienteController  {

    @Autowired
    PacienteRepository repository;

    @PostMapping()
    public ResponseEntity criarPaciente(@RequestBody  @Valid DadosPaciente dados, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(dados);
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        repository.save(paciente);

        return ResponseEntity.created(uri).build();
    }


    @GetMapping
    public ResponseEntity<Page<ListarDadosPaciente>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var pacientes = repository.findByAtivoTrue(pageable).map(ListarDadosPaciente::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarDadosPaciente> listarUmPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new ListarDadosPaciente(paciente));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atuizarPaciente(@RequestBody DadosAtualizacaoPaciente body, @PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        paciente.atualizar(body);

        return ResponseEntity.ok(body);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        paciente.desativarCadastro();

        return ResponseEntity.noContent().build();
    }


}
