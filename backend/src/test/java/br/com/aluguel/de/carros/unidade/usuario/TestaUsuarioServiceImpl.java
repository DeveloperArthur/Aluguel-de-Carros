package br.com.aluguel.de.carros.unidade.usuario;

import br.com.aluguel.de.carros.usuario.Usuario;
import br.com.aluguel.de.carros.usuario.UsuarioRepository;
import br.com.aluguel.de.carros.usuario.UsuarioService;
import br.com.aluguel.de.carros.usuario.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UsuarioServiceImpl.class})
public class TestaUsuarioServiceImpl {

    @MockBean
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;

    private Usuario usuario;

    @Before
    public void setup(){
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setPerfil("cliente");
        Optional<Usuario> usuarioOpt = Optional.of(usuario);
        Mockito.when(repository.findById(1l)).thenReturn(usuarioOpt);
        this.usuario = usuario;
    }

    @Test
    public void aoBuscarUmUsuarioExistente_RetornarUmUsuarioOptional(){
        Mockito.when(service.usuario(1l)).thenReturn(Optional.ofNullable(this.usuario));
        Optional<Usuario> resultado = service.usuario(1l);
        Usuario usuario = resultado.get();

        Assertions.assertThat(usuario.getId()).isEqualTo(1l);
    }
}
