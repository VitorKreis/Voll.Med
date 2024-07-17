package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioDaConsulta implements ValidadoresDeConsulta {

    public void validar(DadosConsulta dados){
        var dataConsulta = dados.data();
        var horaAgora = LocalDateTime.now();
        var AgendadaComAntecedencia = Duration.between(horaAgora, dataConsulta).toMinutes();

        if(AgendadaComAntecedencia < 30){
            throw new ValidacaoException("Consulta deve ser marcada com 30 minutos de antecedencia!");
        }
    }
}
