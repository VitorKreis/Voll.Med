package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsultaConsultaNoHorarioMarcado implements ValidadoresDeConsulta {

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosConsulta dados){
        var medicoComConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if(medicoComConsultaNoMesmoHorario){
            throw new ValidacaoException("Consulta marcada no horario de outra consulta");
        }
    }

}
