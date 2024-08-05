package Med.Voll.Api_Rest.Medico;

import Med.Voll.Api_Rest.Endereco.Endereco;
import Med.Voll.Api_Rest.Endereco.dadosCadastroEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Map;

public record cadastroMedicoResponse(String nome, String email, String telefone, String crm, Tipo tipo, Especialidade especialidade,  Endereco endereco){
    public cadastroMedicoResponse(Medico dados){
        this(dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCrm(), dados.getTipo(), dados.getEspecialidade(), dados.getEndereco());
    }
}
