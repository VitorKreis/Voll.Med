package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamento;
import java.time.LocalDateTime;

public record ListaDadosConsulta(Long id, Long medico_id, Long paciente_id, LocalDateTime data, MotivoCancelamento motivoCancelamento) {

    public ListaDadosConsulta(Consulta consultas) {
        this(consultas.getId(), consultas.getMedico().getId(), consultas.getPaciente().getId(), consultas.getData(), consultas.getCancelamento());
    }


}
