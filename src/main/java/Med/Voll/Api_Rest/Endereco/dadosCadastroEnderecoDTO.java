package Med.Voll.Api_Rest.Endereco;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record dadosCadastroEnderecoDTO(

        @NotNull
        String rua,

        @NotNull
        String numero,

        @NotNull
        String bairro,

        @NotNull
        @Pattern(regexp = "\\d{8}", message = "Cep deve conter 8 numeros")
        String cep,

        @NotNull
        String uf,

        String complemento
) {}
