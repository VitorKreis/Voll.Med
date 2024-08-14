package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.Endereco;

public record ListarPacientes(String nome, String telefone, String email, String cpf, String comentario, tipoAtendimento tipoAtendimento, Endereco endereco, Boolean ativo) {

    public ListarPacientes(Paciente dados){
        this(dados.getNome(), dados.getTelefone(), dados.getEmail(), dados.getCpf(), dados.getComentario(), dados.getAtendimento(), dados.getEndereco(), dados.getAtivo());
    }


}
