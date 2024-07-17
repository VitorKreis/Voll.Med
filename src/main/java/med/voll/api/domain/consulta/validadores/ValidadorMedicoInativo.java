package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo implements ValidadoresDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosConsulta dados){

        var medicoAtivo = repository.findAtivoByID(dados.idMedico());

        if(!medicoAtivo){
            throw  new ValidacaoException("Consulta agendada para um medico inativo no sistema!");
        }

    }

}
