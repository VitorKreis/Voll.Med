package Med.Voll.Api_Rest.domain.Paciente;


import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@DataJpaTest
@ActiveProfiles("teste")
public class CadastrarPacienteTest {


    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retonar um erro, por falta do email")
    void cadastrarPacienteCenarioSemEmail(){
        var paciente = new Paciente(new DadosPaciente("Vitor", null, "419999999", "12312312312", "comentario", tipoAtendimento.ELETIVA, endereco()));

        assertThatThrownBy(() -> entityManager.persist(paciente), null, ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retonar um erro, pela falta do cpf")
    void cadastrarPacienteCenarioComCpfIncorreto(){
        var paciente = new Paciente(new DadosPaciente("Vitor", null, "419999999", null, "comentario", tipoAtendimento.ELETIVA, endereco()));

        assertThatThrownBy(() -> entityManager.persist(paciente), null, ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retonar um erro, porque tem dois cpf iguais")
    void cadastrarPacienteCenarioCpfIguais(){
        var paciente_1 = new Paciente(new DadosPaciente("Vitor", "vitor@email.com", "419999999", "12312312332", "comentario", tipoAtendimento.ELETIVA, endereco()));
        entityManager.persist(paciente_1);
        var paciente_2 = new Paciente(new DadosPaciente("Vitor", "vitor.kreis@email.com", "419999999", "12312312332", "comentario", tipoAtendimento.ELETIVA, endereco()));

        assertThatThrownBy(() -> entityManager.persist(paciente_2), null, ConstraintViolationException.class);
    }


    @Test
    @DisplayName("Deve retonar um erro, porque tem dois emails iguais")
    void cadastrarPacienteCenarioEmailIgual (){
        var paciente_1 = new Paciente(new DadosPaciente("Vitor", "vitor@email.com", "419999999", "12312312332", "comentario", tipoAtendimento.ELETIVA, endereco()));
        entityManager.persist(paciente_1);
        var paciente_2 = new Paciente(new DadosPaciente("Vitor", "vitor@email.com", "419999999", "32132132112", "comentario", tipoAtendimento.ELETIVA, endereco()));

        assertThatThrownBy(() -> entityManager.persist(paciente_2), null, ConstraintViolationException.class);
    }


    @Test
    @DisplayName("Deve retonar um paciente")
    void cadastrarPacienteCenarioPaciente (){
        var paciente = new Paciente(new DadosPaciente("Vitor", "vitor@email.com", "419999999", "12312312332", "comentario", tipoAtendimento.ELETIVA, endereco()));

        assertThat(entityManager.persist(paciente).getClass()).isEqualTo(Paciente.class);
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
