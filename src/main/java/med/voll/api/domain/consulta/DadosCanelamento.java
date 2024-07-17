package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosCanelamento(
        @JsonAlias({"id_consulta", "consulta_id"})
        Long idConsulta,
        @JsonAlias({"motivo", "motivo_cancelamento"})
        MotivoCancelamento cancelamento) {
}
