package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validadorPacienteComConsultaNoMesmoHorario implements ValidadorConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();

        var primieroHorario = dataConsulta.withHour(7);
        var ultimoHorario = dataConsulta.withHour(18);

        var pacientePossuiOutraConsultaNoMesmoDia = repository.existsByPacienteIdAndDataBetween(dados.id_paciente(), primieroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoMesmoDia){
            throw new ValidacaoException("Consulta marcada no horario de outra consulta");
        }
    }
}
