package Med.Voll.Api_Rest.Endereco;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record atualizaEnderecoDTO(String rua, String numero, String bairro,
                                  @Pattern(regexp = "\\d{8}", message = "Cep deve conter 8 numeros")
                                  String cep,
                                  String uf) {
    public atualizaEnderecoDTO(atualizaEnderecoDTO endereco) {
        this(endereco.rua(), endereco.numero(), endereco.bairro(), endereco.cep(), endereco.uf());
    }
}
