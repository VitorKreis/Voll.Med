package Med.Voll.Api_Rest.Controller;

import Med.Voll.Api_Rest.domain.Endereco.DadosAtualizacaoEndereco;
import Med.Voll.Api_Rest.domain.Endereco.DadosEndereco;
import Med.Voll.Api_Rest.domain.Medico.DadosMedico;
import Med.Voll.Api_Rest.domain.Medico.Especialidade;
import Med.Voll.Api_Rest.domain.Medico.Medico;
import Med.Voll.Api_Rest.domain.Medico.Tipo;
import Med.Voll.Api_Rest.domain.Paciente.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ActiveProfiles("teste")
@AutoConfigureMockMvc
@WithMockUser(authorities = "GERENTE")
@AutoConfigureJsonTesters
class pacienteControllerTest {

    @Autowired
    MockMvc mvc;


    @MockBean
    PacienteRepository repository;

    @Autowired
    JacksonTester<DadosPaciente> dadosPaciente;


    @Autowired
    JacksonTester<DadosAtualizacaoPaciente> dadosAtualizacaoPaciente;


    @Test
    @DisplayName("Dever retornar codigo 200")
    void consultarPacienteCenarioPeloId() throws Exception {

        when(repository.getReferenceById(ArgumentMatchers.anyLong())).thenReturn(new Paciente());

        var response = mvc.perform(get("/paciente/20")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    @DisplayName("Deve retornar codigo 200, paciente  cadastrado")
    void cadastrarPacienteCenarioPacienteCadastrado() throws Exception{

        var dadosCadastro = paciente();

        when(repository.save(ArgumentMatchers.any())).thenReturn(new Paciente(dadosCadastro));

        var response = mvc.perform(post("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosPaciente.write(dadosCadastro).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 400, por conta de dados invalidos")
    void cadastrarPacienetCenarioDadosInvalidos() throws Exception{
        var response = mvc.perform(post("/paciente")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 400, por conta de dados invalidos")
    @WithMockUser(authorities = "USER")
    void cadastrarPacienteCenarioAcessoInvalido() throws Exception{
        var response = mvc.perform(post("/paciente")).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 200, por paciente atualizado")
    void atualizarPacienteCenarioPacienteAtualiado() throws Exception {

        var paciente = paciente();

        var pacienteAtualizado = new DadosAtualizacaoPaciente(null, paciente.nome(), paciente.email(), paciente.telefone(), "32132132112", paciente.comentario(), new DadosAtualizacaoEndereco("rua xpto", "bairro", "00000000", "12345678", "DF"));

        when(repository.getReferenceById(ArgumentMatchers.anyLong())).thenReturn(new Paciente(paciente));

        var response = mvc.perform(put("/paciente/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAtualizacaoPaciente.write(pacienteAtualizado).getJson())).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 400, por conta de dados invalidos")
    void atualizarPacienteCenarioDadosInvalidos() throws Exception{
        var response = mvc.perform(put("/paciente/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Dever retornar codigo 403, por credenciais invalidas")
    @WithMockUser(authorities = "USER")
    void atualizarPacienteCenarioAcessoInvalido() throws Exception{
        var response = mvc.perform(put("/paciente/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }


    @Test
    @DisplayName("Dever retornar codigo 200")
    void deletePacienteCenarioPacienteDeletado() throws Exception {

        when(repository.getReferenceById(anyLong())).thenReturn(new Paciente(paciente()));

        var response = mvc.perform(delete("/paciente/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private DadosPaciente paciente(){
        return new DadosPaciente("Paciente", "paciente@email.com", "4199999999", "12312312331", "comenatario", tipoAtendimento.ELETIVA, endereco());
    }



    private DadosEndereco endereco() {
        return new DadosEndereco("rua xpto", "bairro", "00000000", "12345678", "DF", null);
    }


}