package Med.Voll.Api_Rest.domain.Medico;

import Med.Voll.Api_Rest.domain.Endereco.Endereco;

public record cadastroMedicoResponse(String nome, String email, String telefone, String crm, Tipo tipo, Especialidade especialidade,  Endereco endereco){
    public cadastroMedicoResponse(Medico dados){
        this(dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCrm(), dados.getTipo(), dados.getEspecialidade(), dados.getEndereco());
    }
}
