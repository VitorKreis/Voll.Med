package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;

import java.time.DayOfWeek;

public class validadorHorarioDeFuncionamento {

    public void validar(criarConsultaDTO dados){
        var dataConsulta = dados.data();

        var domigo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;

        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if(domigo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta marcada fora do horario de funcionamento !");
        }
    }
}
