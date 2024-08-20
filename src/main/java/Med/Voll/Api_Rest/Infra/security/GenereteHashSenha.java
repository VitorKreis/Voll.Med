package Med.Voll.Api_Rest.Infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenereteHashSenha {
    public static void main(String[] args) {
        String senhaCriptografado = new BCryptPasswordEncoder().encode("43215");
        System.out.println(senhaCriptografado);
    }
}
