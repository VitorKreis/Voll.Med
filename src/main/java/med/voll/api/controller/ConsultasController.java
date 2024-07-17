package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody DadosConsulta dados) {
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listaConsultas(){
        var consultas = repository.findAll();

        return ResponseEntity.ok(consultas);
    }



    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody DadosCanelamento dados){
        consultaService.cancelar(dados);

        return ResponseEntity.noContent().build();
    }

}
