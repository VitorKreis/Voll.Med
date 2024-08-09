package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Consulta.MotivoCancelamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class validadorHorarioCancelamento implements ValidadorCancelamentoConsulta {


    @Autowired
    ConsultaRepository repository;

    public void validar(MotivoCancelamentoDTO motivo){
        var consulta = repository.getReferenceById(motivo.consulta_id());
        var dataConsulta = consulta.getData();
        var horaAgora = LocalDateTime.now();
        var AgendadaComAntecedencia = Duration.between(horaAgora, dataConsulta).toHours();


        if(AgendadaComAntecedencia < 24){
            throw new ValidacaoException("Necessario cancelar a consulta com 24hrs de antecedencia");
        }
    }

}
