package Med.Voll.Api_Rest.Medico;



public record listarMedicosDTO(String nome, String email, String telefone, String crm, Especialidade especialidade, Tipo tipo) {

    public listarMedicosDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getTipo());
    }

}
