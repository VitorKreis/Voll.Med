package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamento;
import java.time.LocalDateTime;

public record ListaConsultaDto(Long id, Medico medico_id, Paciente paciente_id, LocalDateTime data, MotivoCancelamento motivoCancelamento) {

    public ListaConsultaDto(Consulta consultas) {
        this(consultas.getId(), consultas.getMedico(), consultas.getPaciente(), consultas.getData(), consultas.getCancelamento());
    }
}
