package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RegraUsuarioComum implements RegraUsuario {

    private final EmailValidator emailValidator;

    public RegraUsuarioComum(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public String getTipo() {
        return "COMUM";
    }

    @Override
    public Usuario criarUsuario(UsuarioDTO dto) {

        emailValidator.validar(dto.email());

        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.tipo()
        );

        usuario.setPontos(0);

        return usuario;
    }
}