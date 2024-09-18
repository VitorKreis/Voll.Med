package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Endereco.DadosAtualizacaoEndereco;
import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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


    @Autowired
    JacksonTester<DadosAtualizacaoMedico> dadosAtualizaMedico;


    @Test
    @DisplayName("Dever retornar codigo 200")
    void consultarMedicoCenarioPeloId() throws Exception {

        when(repository.getReferenceById(ArgumentMatchers.anyLong())).thenReturn(new Medico());

        var response = mvc.perform(get("/medico/20")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retonar codigo 201, medico criado com sucesso")
    void CadadastrarMedicoCenarioMedicoCriado() throws Exception {

        var dadoMedico = medico();

        when(repository.save(ArgumentMatchers.any())).thenReturn(new Medico(dadoMedico));

        var response = mvc.perform(post("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosMedico.write(dadoMedico).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Dever retornar codigo 400, por informaçoes invalidas")
    void CadadastrarMedicoCenarioInformacoesInvalidas() throws Exception {

        var response = mvc.perform(post("/medico")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 403, por falta de credenciais")
    @WithMockUser(authorities = "USER")
    void CadadastrarMedicoCenarioAcessoInvalido() throws Exception {

        var response = mvc.perform(post("/medico")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 200, por inforamaçoes do medico atualizadas com sucesso")
    void AtualizarMedicoCenarioMedicoAtualizado() throws Exception {

        var dadoMedico = medico();

        var dadosAtualizados = new DadosAtualizacaoMedico("Medico", dadoMedico.email(), dadoMedico.telefone(), new DadosAtualizacaoEndereco("rua xpto", "bairro", "00000000", "12345678", "DF"));

        when(repository.getReferenceById(anyLong())).thenReturn(new Medico(dadoMedico));

        var response = mvc.perform(put("/medico/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAtualizaMedico.write(dadosAtualizados).getJson())).andReturn().getResponse();



        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Dever retornar codigo 400, por informaçoes invalidas")
    void AtualiozarMedicoCenarioInformacoesInvalidas() throws Exception {

        var response = mvc.perform(put("/medico/20")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Dever retornar codigo 403, por falta de credenciais")
    @WithMockUser(authorities = "USER")
    void AtualizarMedicoCenarioAcessoInvalido() throws Exception {

        var response = mvc.perform(put("/medico")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Dever retornar codigo 200")
    void DeleteMedicoCenarioMedicoDeletado() throws Exception {

        when(repository.getReferenceById(anyLong())).thenReturn(new Medico(medico()));

        var response = mvc.perform(delete("/medico/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }





    private DadosMedico medico(){
        return new DadosMedico("Vitor", "vitor.med@vollmed.com", "4199999999", "12334", Especialidade.NEUROLOGIA, Tipo.CLINICO_GERAL, endereco());
    }

    private DadosEndereco endereco() {
        return new DadosEndereco("rua xpto", "bairro", "00000000", "12345678", "DF", null);
    }
}