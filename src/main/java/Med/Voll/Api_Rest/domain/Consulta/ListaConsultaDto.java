package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;

import java.time.LocalDateTime;

public record ListaConsultaDto(Medico medico_id, Paciente paciente_id, LocalDateTime data) {
    public enum MotivoCancelamento {
        MUDANCA_PLANOS,
        INDISPONIBILIDADE_TEMPO,
        TRANSPORTE,
        COMPROMISSO,
        MOTIVOS_PESSOAIS
    }
}
