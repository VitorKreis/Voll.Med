package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("teste")
public class PacienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MedicoRepository repository;

    @Test
    @DisplayName("Deve retornar false porque o id não existe")
    void findAtivoByIdCenarioIdInexistente(){

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
