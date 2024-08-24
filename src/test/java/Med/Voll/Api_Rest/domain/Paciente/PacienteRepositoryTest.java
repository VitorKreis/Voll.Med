package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("teste")
public class PacienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PacienteRepository repository;

    @Test
    @DisplayName("Deve retornar false porque o id não existe")
    void findAtivoByIdCenarioIdInexistente(){
        var paciente = cadastrarPaciente("Josue", "josue.josue@email.com", "12312312331");

        var paciente_ativo = repository.findAtivoById(10l);

        assertThat(paciente_ativo).isNull();
    }


    @Test
    @DisplayName("Deve retornar null porque paciente inativo")
    void findAtivoByIdCenarioPacienteInativo(){
        var paciente = cadastrarPaciente("Josue", "josue.josue@email.com", "12312312331");

        paciente.desativarCadastro();

        var paciente_ativo = repository.findAtivoById(paciente.getId());

        assertThat(paciente_ativo).isNull();
    }


    @Test
    @DisplayName("Deve retornar o paciente pelo seu id")
    void findAtivoByIdCenarioPacientePeloId(){
        var paciente = cadastrarPaciente("Josue", "josue.josue@email.com", "12312312331");

        var paciente_ativo = repository.findAtivoById(paciente.getId());

        assertThat(paciente_ativo).isTrue();
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
