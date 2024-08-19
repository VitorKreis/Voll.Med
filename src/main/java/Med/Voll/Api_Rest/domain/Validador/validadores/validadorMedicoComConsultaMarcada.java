package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validadorMedicoComConsultaMarcada implements ValidadorConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamento dados){
        var medicoComConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.id_medico(), dados.data());

        if(medicoComConsultaNoMesmoHorario){
            throw new ValidacaoException("Consulta marcada no horario de outra consulta");
        }
    }
}
