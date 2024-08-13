package Med.Voll.Api_Rest.domain.Validador.validadores;

import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;

public interface ValidadorCancelamentoAgendamento {
    void validar(MotivoCancelamentoDTO motivo);
}
