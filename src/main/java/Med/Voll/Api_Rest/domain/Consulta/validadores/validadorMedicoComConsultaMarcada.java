package Med.Voll.Api_Rest.domain.Consulta.validadores;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaRepository;
import Med.Voll.Api_Rest.domain.Consulta.criarConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class validadorMedicoComConsultaMarcada {

    @Autowired
    private ConsultaRepository repository;

    public void validar(criarConsultaDTO dados){
        var medicoComConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.id_medico(), dados.data());

        if(medicoComConsultaNoMesmoHorario){
            throw new ValidacaoException("Consulta marcada no horario de outra consulta");
        }
    }
}
