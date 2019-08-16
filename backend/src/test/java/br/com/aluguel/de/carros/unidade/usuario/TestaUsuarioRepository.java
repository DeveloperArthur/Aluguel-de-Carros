package br.com.aluguel.de.carros.unidade.usuario;

import br.com.aluguel.de.carros.endereco.Endereco;
import br.com.aluguel.de.carros.endereco.EnderecoRepository;
import br.com.aluguel.de.carros.usuario.Usuario;
import br.com.aluguel.de.carros.usuario.UsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class TestaUsuarioRepository {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository repository;

    @Before
    public void setup() {

        Endereco e = new Endereco();
        e.setBairro("grajau");
        e.setCidade("grajau");
        e.setComplemento("nao");
        e.setEstado("grajau");
        e.setLogradouro("miriam");
        e.setNumero(1);

        enderecoRepository.save(e);

        Usuario u = new Usuario();
        u.setTelefone("9999");
        u.setSenha("123");
        u.setPerfil("cliente");
        u.setNome("Arthur");
        u.setEndereco(e);
        u.setEmail("arthur@multiedro");
        u.setCpf("*******");
        u.setCnh("3434234");
        u.setId(1l);
        repository.save(u);
    }

    @Test
    public void AoDeletarUsuario_DeletaUsuario() {
        Optional<Usuario> usuario = repository.findById(1l);
        repository.delete(usuario.get());
        Optional<Usuario> usuarioDeletado = repository.findById(1l);
        Assert.assertEquals((usuarioDeletado.isPresent()), false);
    }

    @Test
    public void AoBuscarUsuario_RetornarUsuario() {
        Optional<Usuario> usuario = repository.findById(1l);
        Assert.assertEquals((usuario.get().getId()==1L), true);
    }

    @Test
    public void AoAtualizarUsuario_RetornarUsuarioAtualizado(){
        Optional<Usuario> usuario = repository.findById(1l);
        usuario.get().setNome("Leandro");
        usuario.get().setPerfil("alugador");
        repository.save(usuario.get());
        Assert.assertEquals(usuario.get().getNome().equals("Leandro"), true);
    }

}
