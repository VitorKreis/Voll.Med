package Med.Voll.Api_Rest.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record criarPacienteDTO(
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
        String comentario


) {
}
