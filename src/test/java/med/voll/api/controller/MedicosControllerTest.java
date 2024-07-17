package med.voll.api.controller;

import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.aspectj.weaver.AjAttribute;
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
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class MedicosControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    JacksonTester<DadosMedico> dadosMedicoJson;

    @Autowired
    JacksonTester<atualizaMedicoResponse> dadosAtualizaMedicoJson;


    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deve retornar codigo 400 quando informaçoes invalidas")
    void cadastrar_cenario_Informacoes_Invalidas() throws Exception {
        var response =  mvc.perform(post("/medicos")).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deve retornar codigo 200 quando informaçoes validas")
    void cadastrar_cenario_Informacoes_Validas() throws Exception {

        var especialidade = Especialidade.GINECOLOGIA;

        var endereço = new DadosEndereco("Rua 2", "Buzios", "curitiba", "pr", "09887652", "123", "" );

        var dadosMedico = new DadosMedico("Medico", "medico@med.voll.com", "999999999", "123456", especialidade, endereço);

        var dadosAtulizaMedico = new atualizaMedicoResponse(null, "Medico", "medico@med.voll.com", "123456", "999999999", especialidade, new Endereco(endereço));

        var jsonEsperado = dadosAtualizaMedicoJson.write(dadosAtulizaMedico).getJson();

        when(repository.save(ArgumentMatchers.any())).thenReturn(new Medico(dadosMedico));

        var response =  mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosMedicoJson.write(dadosMedico).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}