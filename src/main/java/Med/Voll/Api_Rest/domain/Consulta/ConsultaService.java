package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import Med.Voll.Api_Rest.domain.Validador.validadores.ValidadorCancelamentoAgendamento;
import Med.Voll.Api_Rest.domain.Validador.validadores.ValidadorConsulta;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import Med.Voll.Api_Rest.domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;


    @Autowired
    ConsultaRepository consultaRepository;


    @Autowired
    List<ValidadorConsulta> validador;

    @Autowired
    List<ValidadorCancelamentoAgendamento> validadorCancelamentoAgendamentos;

    public ListaDadosConsulta agendar(DadosConsulta dados){

        if(!pacienteRepository.existsById(dados.id_paciente())){
            throw new ValidacaoException("Id do paciente informado inexistente");
        }

        if(dados.id_medico() != null && !medicoRepository.existsById(dados.id_medico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }


        DadosAgendamento agendamento = new DadosAgendamento(dados.id_medico(), dados.id_medico(), dados.data(), dados.especialidade());

         validador.forEach(val -> val.validar(agendamento));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.id_paciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);


        consultaRepository.save(consulta);

        return new ListaDadosConsulta(consulta);
    }

    private Medico escolherMedico(DadosConsulta dados){
        if(dados.id_medico() != null){
            return medicoRepository.getReferenceById(dados.id_medico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelar(MotivoCancelamentoDTO motivo) {

        if(!consultaRepository.existsById(motivo.consulta_id())){
            throw new ValidacaoException("Consulta não encontrada na base de dados!");
        }

        validadorCancelamentoAgendamentos.forEach(validadorCancelamentoAgendamento -> validadorCancelamentoAgendamento.validar(motivo));

        var consulta = consultaRepository.getReferenceById(motivo.consulta_id());

        consulta.cancelar(motivo.motivo());
    }
}
