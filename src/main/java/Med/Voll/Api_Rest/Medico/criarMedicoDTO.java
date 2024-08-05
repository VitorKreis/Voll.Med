package Med.Voll.Api_Rest.Medico;


import Med.Voll.Api_Rest.Endereco.dadosCadastroEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record criarMedicoDTO(
        @NotNull(message = "Nome não pode estar vazio")
        String nome,

        @NotNull(message = "Email não pode estar vazio")
        @Email(message = "Email deve ser valido")
        String email,

        @NotNull(message = "Telefone não pode estar vazio")
        @Pattern(regexp = "\\d{9,11}")
        String telefone,

        @NotNull(message = "Crm não pode estar vazio")
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull(message = "Especilidade não pode estar vazio")
        Especialidade especialidade,

        @NotNull(message = "Tipo não pode estar vazio")
        Tipo tipo,

        @NotNull(message = "Endereço não pode estar vazio")
        @Valid
        dadosCadastroEnderecoDTO endereco
) {
}
