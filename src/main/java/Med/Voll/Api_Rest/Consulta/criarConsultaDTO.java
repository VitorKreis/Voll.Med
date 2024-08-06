package Med.Voll.Api_Rest.Consulta;


import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record criarConsultaDTO(Long id_medico, Long id_paciente, LocalDateTime data) {
}
