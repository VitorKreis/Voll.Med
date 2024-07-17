package med.voll.api.controller;

import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ConsultasControllerTest {
    @Autowired
    MockMvc mvc;


    @Autowired
    JacksonTester<DadosConsulta> dadosConsultaJson;

    @Autowired
    JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deve retornar codiogo 400 quando informaçoes invalidas")
    void agendar_cenario_Codigo400() throws Exception {

            var response = mvc.perform(post("/consultas")).andReturn().getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }


    @Test
    @DisplayName("Deve retornar codigo 200 quando informaçoes validas")
    void agendar_cenario_Codigo200() throws Exception {

       var data = LocalDateTime.now().plusHours(1);
       var especialidade = Especialidade.GINECOLOGIA;

       var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 1l, 2l, data);

        when(consultaService.agendar(any())).thenReturn(dadosDetalhamento);

       var response = mvc.perform(post("/consultas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dadosConsultaJson.write(new DadosConsulta(1l, 2l, data, especialidade)).getJson())).andReturn().getResponse();

       var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}