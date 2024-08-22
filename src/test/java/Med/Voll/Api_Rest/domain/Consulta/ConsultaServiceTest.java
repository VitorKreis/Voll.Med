package Med.Voll.Api_Rest.domain.Consulta;

import Med.Voll.Api_Rest.Infra.exceptions.ValidacaoException;
import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.DadosMedico;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.Tipo;
import Med.Voll.Api_Rest.domain.Paciente.DadosPaciente;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;
import Med.Voll.Api_Rest.domain.Paciente.tipoAtendimento;
import Med.Voll.Api_Rest.domain.Validador.DadosAgendamento;
import Med.Voll.Api_Rest.domain.Validador.validadores.validadorHorarioDeFuncionamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("teste")
class ConsultaServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    private ConsultaService service;

    @Test()
    @DisplayName("Deve retonar null quando consulta marcada fora do horario de funcionamento")
    void agendarConsultaForaDoHorario() {

        var data_consulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(5, 0);

        var medico = cadastrarMedico("Vitor", "vitor.med@medvoll.com", "123456", Especialidade.CARDIOLOGIA);

        var paciente = cadastrarPaciente("Luiz", "luiz.luiz@email.com", "12312312309");

        var consulta = cadastrarConsulta(medico, paciente, data_consulta);

        var dados = new DadosAgendamento(consulta.getMedico().getId() ,consulta.getPaciente().getId(), consulta.getData(),  Especialidade.CARDIOLOGIA);

        var validador = new validadorHorarioDeFuncionamento();

        assertThrowsExactly(ValidacaoException.class, () -> validador.validar(dados), "Consulta marcada fora do horario de funcionamento !");
    }

    private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data ){
        var consulta = new Consulta(null, medico, paciente, data, null);
        entityManager.persist(consulta);
        return consulta;
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade){
        var medico = new Medico(new DadosMedico(nome, email ,"419999999", crm, especialidade, Tipo.valueOf("ESPECIALISTA"),  endereco()));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf){
        var paciente = new Paciente(new DadosPaciente(nome, email, "419999999", cpf, "Paciente aprensenta palidez", tipoAtendimento.ELETIVA, endereco()));
        entityManager.persist(paciente);
        return paciente;
    }


    private DadosEndereco endereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "PR",
                "DF",
                null
        );
    }

}