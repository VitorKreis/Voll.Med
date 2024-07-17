package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosListaConsultas(Long idPaciente, Long idMedico, LocalDateTime data) {
}
