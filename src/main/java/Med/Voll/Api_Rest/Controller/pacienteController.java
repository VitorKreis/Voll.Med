package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.Paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class pacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping()
    public ResponseEntity criarPaciente(@RequestBody  @Valid criarPacienteDTO dados){
        var paciente = new Paciente(dados);
        repository.save(paciente);

        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<ListarPacientes>> listarPacientes(){
        var pacientes = repository.findByAtivoTrue().stream().map(ListarPacientes::new).toList();
        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarPacientes> listarUmPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new ListarPacientes(paciente));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atuizarPaciente(@RequestBody atualizarPacienteDTO body, @PathVariable Long id){
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
