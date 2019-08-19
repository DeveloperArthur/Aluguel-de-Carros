package br.com.aluguel.de.carros.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> todos();

    Optional<Usuario> usuario(Long id);

    Optional<Usuario> buscaUsuarioPorEmail(String email);

    boolean validaAcessoDoUsuario(Usuario usuario, String senha);

    Usuario novo(Usuario usuario);

    Usuario atualiza(Usuario usuario);

    boolean deleta(Long id);
}
