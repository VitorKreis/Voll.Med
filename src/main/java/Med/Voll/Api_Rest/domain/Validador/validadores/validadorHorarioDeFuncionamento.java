package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class validadorHorarioDeFuncionamento implements ValidadorConsulta {

    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();

        var domigo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesDaAberturaDaClinica = dataConsulta.getHour() < 8;

        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if(domigo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta marcada fora do horario de funcionamento !");
        }
    }
}
