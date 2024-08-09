package Med.Voll.Api_Rest.domain.Medico;



public record listarMedicosDTO(String nome, String email, String telefone, String crm, Especialidade especialidade, Tipo tipo, Boolean ativo) {

    public listarMedicosDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getTipo(), medico.getAtivo());
    }

}
