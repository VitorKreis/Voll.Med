package Med.Voll.Api_Rest.Paciente;

import Med.Voll.Api_Rest.Endereco.atualizaEnderecoDTO;
import Med.Voll.Api_Rest.Endereco.dadosCadastroEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record atualizarPacienteDTO(
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
        atualizaEnderecoDTO endereco
) {
}
