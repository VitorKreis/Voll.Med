package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar null quando um unico medico cadastrado nao esta disponivel")
    void escolherMedicoAleatorioLivreNaDataCenarioNull() {

        //given ou arrange
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);
        var medico = cadastrarMedico("Marcos", "marcos@med.voll.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente =  cadastrarPaciente("Maria", "maria@email.com", "00000000000");
       cadastrarConsulta(medico, paciente, proximaSegunda);

       //when ou act
        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegunda);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }


    @Test
    @DisplayName("Deve retornar um Medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenarioMedico() {

        //given ou arrange
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);
        var medico = cadastrarMedico("Marcos", "marcos@med.voll.com", "123456", Especialidade.CARDIOLOGIA);

        //when ou act
        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegunda);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }




    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "PR",
                "DF",
                null,
                null
        );
    }
}