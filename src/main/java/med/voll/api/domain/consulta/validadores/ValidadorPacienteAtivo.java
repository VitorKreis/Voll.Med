package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadoresDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosConsulta dados){

        var pacienteAtivo = repository.findAtivoByID(dados.idPaciente());
        if(!pacienteAtivo){
            throw  new ValidacaoException("Consulta agendada para um paciente inativo no sistema!");
        }



    }
}
