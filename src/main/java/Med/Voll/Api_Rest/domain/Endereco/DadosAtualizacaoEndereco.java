package Med.Voll.Api_Rest.domain.Endereco;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEndereco(String rua, String numero, String bairro,
                                       @Pattern(regexp = "\\d{8}", message = "Cep deve conter 8 numeros")
                                  String cep,
                                       String uf) {
    public DadosAtualizacaoEndereco(DadosAtualizacaoEndereco endereco) {
        this(endereco.rua(), endereco.numero(), endereco.bairro(), endereco.cep(), endereco.uf());
    }
}
