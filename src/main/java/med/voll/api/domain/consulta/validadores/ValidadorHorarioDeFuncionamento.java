package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioDeFuncionamento implements ValidadoresDeConsulta {

    public void validar(DadosConsulta dados){
        var dataConsulta = dados.data();

        var domigo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;

        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if(domigo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta marcada fora do horario de funcionamento !");
        }
    }

}
