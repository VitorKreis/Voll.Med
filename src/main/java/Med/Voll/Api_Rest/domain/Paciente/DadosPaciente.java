package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record DadosPaciente(
        @NotNull(message = "Nome não pode ser vazio")
        String nome,

        @NotNull(message = "Nome não pode ser vazio")
        @Email(message = "Email deve ser valido")
        String email,

        @NotNull(message = "Telefone não pode estar vazio")
        @Pattern(regexp = "\\d{9,11}")
        String telefone,

        @NotNull(message = "Nome não pode ser vazio")
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull(message = "Nome não pode ser vazio")
        String comentario,

        @NotNull(message = "Tipo de atedimento não pode ser vazio")
        @JsonAlias("atendimento")
        tipoAtendimento atendimento,

        @NotNull(message = "endereço não pode ser vazio")
        @Valid
        DadosEndereco endereco
) {
}
