package Med.Voll.Api_Rest.Medico;

import Med.Voll.Api_Rest.Endereco.atualizaEnderecoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record atualizarMedicoDTO(@NotNull Long id, String nome,
                                 @Email(message = "Email deve ser valido")
                                 String email,
                                 @Pattern(regexp = "\\d{9,11}")
                                 String telefone,
                                 atualizaEnderecoDTO endereco) {}
