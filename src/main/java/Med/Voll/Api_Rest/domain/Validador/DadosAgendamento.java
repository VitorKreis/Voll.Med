package Med.Voll.Api_Rest.domain.Validador;

import java.time.LocalDateTime;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;


public record DadosAgendamento(
        Long id_medico,

        Long id_paciente,

        LocalDateTime data,

        Especialidade especialidade) {}
