package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.Infra.ValidacaoException;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import Med.Voll.Api_Rest.domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;


    @Autowired
    ConsultaRepository consultaRepository;

    public void agendar(criarConsultaDTO dados){


        if(!pacienteRepository.existsById(dados.id_paciente())){
            throw new ValidacaoException("Id do paciente informado inexistente");
        }

        if(dados.id_medico() != null && !medicoRepository.existsById(dados.id_medico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.id_paciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());


        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(criarConsultaDTO dados){
        if(dados.id_medico() != null){
            return medicoRepository.getReferenceById(dados.id_medico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

}
