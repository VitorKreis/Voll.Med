package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCanelamento;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ValidadorHorarioDoCancelamento implements ValidarCancelamentoDeConsulta {

    @Autowired
    ConsultaRepository repository;

    public void validar(DadosCanelamento dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var dataConsulta = consulta.getData();
        var horaAgora = LocalDateTime.now();
        var AgendadaComAntecedencia = Duration.between(horaAgora, dataConsulta).toHours();


        if(AgendadaComAntecedencia < 24){
            throw new ValidacaoException("Necessario cancelar a consulta com 24hrs de antecedencia");
        }
    }
}
