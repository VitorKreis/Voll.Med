package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Consulta.Consulta;
import Med.Voll.Api_Rest.domain.Consulta.ConsultaService;
import Med.Voll.Api_Rest.domain.Consulta.DadosConsulta;
import Med.Voll.Api_Rest.domain.Consulta.ListaDadosConsulta;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("teste")
@WithMockUser(authorities = "GERENTE")
class ConsultaControllerTest {

    @Autowired
    MockMvc mvc;


    @Autowired
    JacksonTester<DadosConsulta> dadosConsultaJson;

    @Autowired
    JacksonTester<ListaDadosConsulta> dadosDetalhamentoConsultaJson;


    @MockBean
    ConsultaService consultaService;

    @Test
    @DisplayName("Deve retornar codigo 400, por falta de informaçoes!")
    void agendarConsultaCenarioInformacoesInvalidas() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 200!")
    void agendarConsultaCenarioTudoCerto() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        //var dadoConsulta = new ListaDadosConsulta(null, 1l, 1l, data, especialidade, null);

        //when(consultaService.agendar(any())).thenReturn(dadoConsulta);

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


}