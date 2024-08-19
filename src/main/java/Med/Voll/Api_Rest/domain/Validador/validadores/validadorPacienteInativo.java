package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import Med.Voll.Api_Rest.domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class validadorPacienteInativo implements ValidadorConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamento dados){

        var pacienteAtivo = repository.findAtivoById(dados.id_paciente());

        if(!pacienteAtivo){
            throw  new ValidacaoException("Consulta agendada para um paciente inativo no sistema!");
        }
    }

}
