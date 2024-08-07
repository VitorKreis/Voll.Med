package Med.Voll.Api_Rest.domain.Paciente;

import Med.Voll.Api_Rest.domain.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "pacientes")
@Entity(name = "Paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    private String cpf;

    private String comentario;

    @Enumerated(EnumType.STRING)
    private tipoAtendimento atendimento;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = true;


    public Paciente(criarPacienteDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.comentario = dados.comentario();
        this.atendimento = dados.atendimento();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizar(atualizarPacienteDTO body) {
        if(body.nome() != null){
            this.nome = body.nome();
        }
        if(body.cpf() != null){
            this.cpf = body.cpf();
        }
        if(body.email() != null){
            this.email = body.email();
        }
        if(body.telefone() != null){
            this.telefone = body.telefone();
        }
        if(body.comentario() != null){
            this.comentario = body.comentario();
        }
        if(body.endereco() != null){
            this.endereco.atualizaEndereco(body.endereco());
        }

    }

    public void desativarCadastro() {
        this.ativo = false;
    }
}
