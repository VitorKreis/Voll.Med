package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadoresDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosConsulta dados){
        var dataConsulta = dados.data();

        var primieroHorario = dataConsulta.withHour(7);
        var ultimoHorario = dataConsulta.withHour(18);

        var pacientePossuiOutraConsultaNoMesmoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primieroHorario, ultimoHorario);

        System.out.println(pacientePossuiOutraConsultaNoMesmoDia);

        if(pacientePossuiOutraConsultaNoMesmoDia){
            throw new ValidacaoException("Paciente so pode ter uma consulta marcada por dia!");
        }
    }
}
