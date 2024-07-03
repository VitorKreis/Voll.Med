package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record atualizaMedicoResponse(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public atualizaMedicoResponse(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }

}
