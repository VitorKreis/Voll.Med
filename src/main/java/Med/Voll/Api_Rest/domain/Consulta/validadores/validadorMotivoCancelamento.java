package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.MotivoCancelamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class validadorMotivoCancelamento implements ValidadorCancelamentoConsulta{

    public void validar(MotivoCancelamentoDTO motivo){
        var motivo_cancelamento = motivo.motivo();

        if (motivo_cancelamento == null){
            throw new ValidacaoException("Necessario ter um motivo ao cancelar uma consulta");
        }
    }

}
