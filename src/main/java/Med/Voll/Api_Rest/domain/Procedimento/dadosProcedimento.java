package Med.Voll.Api_Rest.domain.Procedimento;

import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record dadosProcedimento(@JsonAlias({"medico_id", "id_medico"})
                                Long id_medico,

                                @JsonAlias({"paciente_id", "id_paciente"})
                                @NotBlank
                                Long id_paciente,

                                @Future
                                @NotBlank
                                LocalDateTime data,

                                Especialidade especialidade) {}
