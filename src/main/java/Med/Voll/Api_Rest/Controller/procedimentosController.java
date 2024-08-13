package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Procedimento.ProcedimentoRepository;
import Med.Voll.Api_Rest.domain.Procedimento.ProcedimentoService;
import Med.Voll.Api_Rest.domain.Procedimento.dadosProcedimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/procedimentos")
public class procedimentosController {

    @Autowired
    ProcedimentoService service;

    @Autowired
    ProcedimentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody dadosProcedimento dados){
        service.agendar(dados);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok(repository.findAll());
    }


}
