package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Endereco.DadosAtualizacaoEndereco;
import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Endereco.Endereco;
import Med.Voll.Api_Rest.domain.Medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@ActiveProfiles("teste")
@AutoConfigureMockMvc
@WithMockUser(authorities = "GERENTE")
@AutoConfigureJsonTesters
class medicoControllerTest {


    @Autowired
    MockMvc mvc;


    @MockBean
    MedicoRepository repository;


    @Autowired
    JacksonTester<DadosMedico> dadosMedico;


    @Test
    @DisplayName("Dever retornar codigo 200")
    void consultarMedicoCenarioTudoCerto() throws Exception {
        var response = mvc.perform(get("/medico")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Dever retornar codigo 403, por id nao encontrado")
    void consultarMedicoCenarioIDInexistente() throws Exception {
        var response = mvc.perform(get("/medico/20")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }



    @Test
    @DisplayName("Deve retonar codigo 201, medico criado com sucesso")
    void criarMedicoCenarioMedicoCriado() throws Exception {
        var especialidade = Especialidade.NEUROLOGIA;

        var endereco = new DadosEndereco("rua xpto", "bairro", "00000000", "12345678", "DF", null);

        var dadoMedico = new DadosMedico("Vitor", "vitor.med@vollmed.com", "4199999999", "12334", especialidade, Tipo.CLINICO_GERAL, endereco);

        when(repository.save(ArgumentMatchers.any())).thenReturn(new Medico(dadoMedico));

        var response = mvc.perform(post("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosMedico.write(dadoMedico).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


}