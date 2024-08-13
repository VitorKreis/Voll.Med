package Med.Voll.Api_Rest.domain.Procedimento;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Validador.MotivoCancelamentoDTO;
import Med.Voll.Api_Rest.domain.Validador.validadores.ValidadorCancelamentoAgendamento;
import Med.Voll.Api_Rest.domain.Validador.validadores.ValidadorConsulta;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import Med.Voll.Api_Rest.domain.Paciente.PacienteRepository;

import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ProcedimentoRepository procedimentoRepository;

    @Autowired
    List<ValidadorConsulta> validador;


    @Autowired
    List<ValidadorCancelamentoAgendamento> validadorCancelamentoAgendamentos;

    public void agendar(dadosProcedimento dados){
        if(!pacienteRepository.existsById(dados.id_paciente())){
            throw new ValidacaoException("Id do paciente informado inexistente");
        }

        if(dados.id_medico() != null && !medicoRepository.existsById(dados.id_medico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        DadosAgendamento dadosAgendamento = new DadosAgendamento(dados.id_medico(), dados.id_medico(), dados.data(), dados.especialidade());

        validador.forEach(val -> val.validar(dadosAgendamento));

        var paciente = pacienteRepository.getReferenceById(dados.id_paciente());

        var medico = escolherMedico(dados);

        var procedimentos = new Procedimentos(null, medico, paciente, dados.data(), null);

        procedimentoRepository.save(procedimentos);
    }


    private Medico escolherMedico(dadosProcedimento dados){

        if(dados.id_medico() != null){
            return medicoRepository.getReferenceById(dados.id_medico());
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }


    public void cancelar(MotivoCancelamentoDTO motivo) {

        if(!procedimentoRepository.existsById(motivo.consulta_id())){
            throw new ValidacaoException("Consulta não encontrada na base de dados!");
        }

        validadorCancelamentoAgendamentos.forEach(validadorCancelamentoAgendamento -> validadorCancelamentoAgendamento.validar(motivo));

        var procedimentos = procedimentoRepository.getReferenceById(motivo.consulta_id());

        procedimentos.cancelar(motivo.motivo());
    }
}
