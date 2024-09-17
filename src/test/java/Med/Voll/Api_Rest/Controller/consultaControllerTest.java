package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Consulta.ConsultaService;
import Med.Voll.Api_Rest.domain.Consulta.DadosConsulta;
import Med.Voll.Api_Rest.domain.Consulta.ListaDadosConsulta;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("teste")
@WithMockUser(authorities = "GERENTE")
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    MockMvc mvc;


    @Autowired
    JacksonTester<DadosConsulta> dadosConsultaJson;


    @Autowired
    JacksonTester<ListaDadosConsulta> listaDadosConsulta;


    @MockBean
    ConsultaService consultaService;


    @Test
    @DisplayName("Deve retornar codigo 200!")
    void agendarConsultaCenarioConsultaCriada() throws Exception {

        var data = LocalDateTime.now().plusHours(1);

        var especialidade = Especialidade.CARDIOLOGIA;

        var dadoConsulta = new ListaDadosConsulta(null, 1L, 1L, data, null);

        when(consultaService.agendar(any())).thenReturn(dadoConsulta);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosConsultaJson.write(new DadosConsulta(1l, 1l, data, especialidade)).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = listaDadosConsulta.write(dadoConsulta).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    @Test
    @DisplayName("Deve retornar codigo 400, por falta de informaçoes!")
    void agendarConsultaCenarioInformacoesInvalidas() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 403, por falta de credenciais")
    @WithMockUser(authorities = "user")
    void agendarConsultaCenarioAcessoInvalido() throws Exception {

        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 200")
    void consultarConsultaCenarioRetonarConsultas() throws Exception {
        var response = mvc.perform(get("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 403, por id nao encontrado")
    void consultarConsultaCenarioRetornarIdInexistente() throws Exception {
        var response = mvc.perform(get("/consultas/10")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }



}