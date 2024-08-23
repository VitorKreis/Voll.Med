package Med.Voll.Api_Rest.domain.Medico;

import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.DadosMedico;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.Tipo;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("teste")
public class CadastrarMedicoTest {


    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar erro quando tentado cadastrar um medico sem nome")
    void cadastrarMedicoSemNome(){
        var medico = new Medico(new DadosMedico(null,
                "vitor@voll.med",
                "419999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        assertThatThrownBy(() -> entityManager.persist(medico), "NULL not allowed for column \"NOME\"; SQL statement", ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retornar erro quando tentando cadastrar dois medicos com o mesmo crm")
    void cadastrarMedicoComMesmoCRM(){
        var medico1 = new Medico(new DadosMedico("vitor",
                "vitor@voll.med",
                "419999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        var medico = new Medico(new DadosMedico("lucas",
                "lucas@voll.med",
                "419999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        entityManager.persist(medico1);

        assertThatThrownBy(() -> entityManager.persist(medico), null, ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retornar erro quando tentando cadastrar um medico sem especialidade ")
    void cadastrarMedicoSemEspecialidade(){
        var medico = new Medico(new DadosMedico("vitor",
                "vitor@voll.med",
                "419999999",
                "123456",
                null,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        assertThatThrownBy(() -> entityManager.persist(medico), null, ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retornar erro quando tentando cadastrar um medico sem email")
    void cadastrarMedicoSemEmail(){
        var medico = new Medico(new DadosMedico("Josue",
                null,
                "419999999",
                "123456",
                Especialidade.NEUROLOGIA,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        assertThatThrownBy(() -> entityManager.persist(medico), null, ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Deve retornar um medico")
    void cadastarMedico(){
        var medico = new Medico(new DadosMedico("Josue",
                "josue@med.voll.com",
                "419999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                Tipo.valueOf("ESPECIALISTA"),
                endereco()));

        assertThat(entityManager.persist(medico).getClass()).isEqualTo(Medico.class);
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
