package Med.Voll.Api_Rest.domain.Medico;

import Med.Voll.Api_Rest.domain.Endereco.DadosAtualizacaoEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoMedico(String nome,
                                     @Email(message = "Email deve ser valido")
                                 String email,
                                     @Pattern(regexp = "\\d{9,11}")
                                 String telefone,
                                     DadosAtualizacaoEndereco endereco) {}
