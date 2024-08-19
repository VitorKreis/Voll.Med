package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class validadorMotivoCancelamento implements ValidadorCancelamentoAgendamento {

    public void validar(MotivoCancelamentoDTO motivo){
        var motivo_cancelamento = motivo.motivo();

        if (motivo_cancelamento == null){
            throw new ValidacaoException("Necessario ter um motivo ao cancelar uma consulta");
        }
    }

}
