package br.com.aluguel.de.carros.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> usuario(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean validaAcessoDoUsuario(Usuario usuario, String senha) {
        if (senha.equals(usuario.getSenha()))
            return true;
        else
            return false;
    }

    @Override
    public Usuario novo(Usuario usuario) {
        usuario.setNome(usuario.getNome());
        return repository.save(usuario);
    }

    @Override
    public Usuario atualiza(Usuario usuario) {
        Usuario usuarioAtualizado = repository.save(usuario);
        //repository.reflesh(usuarioAtualizado);
        return usuarioAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Usuario> usuarioOpt = usuario(id);
        if (usuarioOpt.isPresent()) {
            repository.delete(usuarioOpt.get());
            return true;
        }
        return false;
    }
}
