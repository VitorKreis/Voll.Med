package med.voll.api.domain.consulta;

import med.voll.api.controller.PacienteController;
import med.voll.api.domain.consulta.validadores.ValidadoresDeConsulta;
import med.voll.api.domain.consulta.validadores.ValidarCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.execptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    List<ValidadoresDeConsulta> validadoresDeConsultas;


    @Autowired
    List<ValidarCancelamentoDeConsulta> validarCancelamentoDeConsultas;

    public DadosDetalhamentoConsulta agendar(DadosConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        validadoresDeConsultas.forEach(val -> val.validar(dados) );

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCanelamento dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validarCancelamentoDeConsultas.forEach(can -> can.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.cancelamento());

    }
}
