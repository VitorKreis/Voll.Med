package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class validadorHoraConsulta {

    public void validar(criarConsultaDTO dados){
        var dataConsulta = dados.data();
        var horaAgora = LocalDateTime.now();
        var AgendadaComAntecedencia = Duration.between(horaAgora, dataConsulta).toMinutes();

        if(AgendadaComAntecedencia < 30){
            throw new ValidacaoException("Consulta deve ser marcada com 30 minutos de antecedencia!");
        }

    }

}
