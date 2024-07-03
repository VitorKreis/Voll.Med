package med.voll.api.domain.medico;

public record dadosListagemMedico(Long id, String nome,String email, String crm, Especialidade especialidade) {

    public dadosListagemMedico(Medico dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}
