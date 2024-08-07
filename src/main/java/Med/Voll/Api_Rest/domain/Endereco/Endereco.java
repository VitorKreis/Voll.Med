package Med.Voll.Api_Rest.domain.Endereco;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String rua;

    private String numero;

    private String bairro;

    private String cep;

    private String uf;

    private String complemento;

    public Endereco(dadosCadastroEnderecoDTO endereco) {
        this.rua = endereco.rua();
        this.numero = endereco.numero();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
    }
    public void atualizaEndereco(atualizaEnderecoDTO endereco){
        if(endereco.rua() != null){
            this.rua = endereco.rua();
        }

        if(endereco.bairro() != null){
            this.bairro = endereco.bairro();
        }

        if(endereco.numero() != null){
            this.numero = endereco.numero();
        }

        if(endereco.cep() != null){
            this.cep = endereco.cep();
        }

        if(endereco.uf() != null){
            this.uf = endereco.uf();
        }
    }
}
