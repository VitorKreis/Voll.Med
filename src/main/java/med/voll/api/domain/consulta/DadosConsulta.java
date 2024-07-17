package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosConsulta(

        @JsonAlias({"id_medico", "medico_id"})
        Long idMedico,
        @NotBlank
        @JsonAlias({"id_paciente", "paciente_id"})
        Long idPaciente,
        @NotBlank
        @Future
        LocalDateTime data,

        Especialidade especialidade
        ) {}
