package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import Med.Voll.Api_Rest.domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class validadorPacienteInativo {

    @Autowired
    private PacienteRepository repository;

    public void validar(criarConsultaDTO dados){

        var pacienteAtivo = repository.findAtivoById(dados.id_paciente());

        if(!pacienteAtivo){
            throw  new ValidacaoException("Consulta agendada para um paciente inativo no sistema!");
        }
    }

}
