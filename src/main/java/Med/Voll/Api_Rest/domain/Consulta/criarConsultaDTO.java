package Med.Voll.Api_Rest.domain.Consulta;


import Med.Voll.Api_Rest.domain.Medico.Especialidade;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record criarConsultaDTO(Long id_medico, Long id_paciente, Especialidade especialidade,  LocalDateTime data) {
}
