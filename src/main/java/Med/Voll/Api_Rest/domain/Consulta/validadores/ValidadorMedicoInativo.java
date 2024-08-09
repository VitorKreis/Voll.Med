package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo implements ValidadorConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(criarConsultaDTO dados){

        if(dados.id_medico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.id_medico());

        if(!medicoAtivo){
            throw  new ValidacaoException("Consulta agendada para um medico inativo no sistema!");
        }

    }
}
