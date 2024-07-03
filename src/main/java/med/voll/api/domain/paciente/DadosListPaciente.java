package med.voll.api.domain.paciente;

public record DadosListPaciente(Long id, String nome, String email, String cpf) {

    public DadosListPaciente(Paciente dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCpf());
    }
}
