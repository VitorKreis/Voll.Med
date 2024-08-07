package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoInativo {

    @Autowired
    private MedicoRepository repository;

    public void validar(criarConsultaDTO dados){

        var medicoAtivo = repository.findAtivoById(dados.id_medico());

        if(!medicoAtivo){
            throw  new ValidacaoException("Consulta agendada para um medico inativo no sistema!");
        }

    }
}
