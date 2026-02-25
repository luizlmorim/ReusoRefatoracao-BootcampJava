package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RegraUsuarioVIP implements RegraUsuario {

    private final EmailValidator emailValidator;

    public RegraUsuarioVIP(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public String getTipo() {
        return "VIP";
    }

    @Override
    public Usuario criarUsuario(UsuarioDTO dto) {

        emailValidator.validar(dto.email());
        validarIdade(dto.idade());

        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.tipo()
        );

        usuario.setPontos(100);

        return usuario;
    }

    private void validarIdade(int idade){
        if(idade < 18)
            throw new IllegalArgumentException("Menor de idade");
    }
}