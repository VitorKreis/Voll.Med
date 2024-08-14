package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.DadosAtualizacaoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        @Email(message = "Email deve ser valido")
        String email,
        @Pattern(regexp = "\\d{9,11}")
        String telefone,
        @Pattern(regexp = "\\d{11}")
        String cpf,
        String comentario,
        @Valid
        DadosAtualizacaoEndereco endereco
) {
}
