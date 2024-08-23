package Med.Voll.Api_Rest.domain.Medico;

import Med.Voll.Api_Rest.domain.Consulta.Consulta;
import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.*;
import Med.Voll.Api_Rest.domain.Paciente.DadosPaciente;
import Med.Voll.Api_Rest.domain.Paciente.Paciente;
import Med.Voll.Api_Rest.domain.Paciente.tipoAtendimento;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("teste")
class MedicoRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MedicoRepository repository;


    @Test
    @DisplayName("Deve retornar null quando tiver so um medico cadastrado e não esta livre na data")
    void escolherMedicoAleatorioLivreNaDataCenarioNull() {

        var data_consulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);
        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Luiz", "luiz@email.com", "12312312345");

        cadastrarConsulta(medico, paciente, data_consulta);


        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, data_consulta);

        assertThat(medicoLivre).isNull();

    }


    @Test
    @DisplayName("Deve retornar um medico quando estiver livre na data")
    void escolherMedicoAleatorioLivreNaDataCenarioMedico() {

        var data_consulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);
        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Luiz", "luiz@email.com", "12312312345");


        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, data_consulta);

        assertThat(medicoLivre).isEqualTo(medico);

    }


    @Test
    @DisplayName("Deve retornar um medico quando estiver livre na data e com a especialidade escolhida")
    void escolherMedicoAleatorioLivreNaDataCenarioEspecialidade() {

        var data_consulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);

        var medicoCardiologia = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);
        var medicoNeurologia = cadastrarMedico("Luiz", "luiz@vollmed.com", "654321", Especialidade.NEUROLOGIA);
        var medicoDermatologia = cadastrarMedico("Claudio", "claudio@vollmed.com", "987654", Especialidade.DERMATOLOGIA);

        var paciente = cadastrarPaciente("Luiz", "luiz@email.com", "12312312345");


        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.NEUROLOGIA, data_consulta);

        assertThat(medicoLivre.getEspecialidade()).isEqualTo(Especialidade.NEUROLOGIA);

    }


    @Test
    @DisplayName("Deve retornar um null porque o medico nao esta ativo")
    void escolherMedicoAleatorioLivreNaDataCenarioNaoAtivo() {

        var data_consulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11, 0);

        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);

        medico.desativarCadastro();

        var paciente = cadastrarPaciente("Luiz", "luiz@email.com", "12312312345");


        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, data_consulta);

        assertThat(medicoLivre).isNull();

    }

    @Test
    @DisplayName("Deve retornar um false porque o medico nao esta ativo")
    void medicoAtivoCenarioMedicoInativo(){
        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);
        medico.desativarCadastro();

        var medico_inativo = repository.findAtivoById(medico.getId());

        assertThat(medico_inativo).isFalse();

    }

    @Test
    @DisplayName("Deve retornar um null porque o id do medico não existe")
    void medicoAtivoCenarioIdInexistente(){
        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);
        medico.desativarCadastro();

        var medico_inativo = repository.findAtivoById(10l);

        assertThat(medico_inativo).isNull();

    }

    @Test
    @DisplayName("Deve retornar um true")
    void MedicoAtivoByIdCenarioTrue(){
        var medico = cadastrarMedico("Vitor", "vitor@vollmed.com", "123456", Especialidade.CARDIOLOGIA);

        var medico_ativo = repository.findAtivoById(medico.getId());

        assertThat(medico_ativo).isTrue();
    }








    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data ){
        entityManager.persist(new Consulta(null, medico, paciente, data, null));
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