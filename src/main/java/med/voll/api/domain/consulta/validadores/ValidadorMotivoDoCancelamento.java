package med.voll.api.domain.consulta.validadores;

import med.voll.api.domain.consulta.DadosCanelamento;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoDoCancelamento implements ValidarCancelamentoDeConsulta {

    public void validar(DadosCanelamento dadosCanelamento){
        var motivo = dadosCanelamento.cancelamento();
        if (motivo == null){
            throw new ValidacaoException("Necessario ter um motivo ao cancelar uma consulta");
        }
    }
}
