package br.com.aluguel.de.carros.unidade.carro;

import br.com.aluguel.de.carros.carro.Carro;
import br.com.aluguel.de.carros.carro.CarroRepository;
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
public class TestaCarroRepository {

    @Autowired
    CarroRepository carrorepository;

    @Autowired
    UsuarioRepository usuariorepository;

    @Autowired
    EnderecoRepository enderecorepository;

    @Before
    public void setup(){
        Endereco e = new Endereco();
        e.setBairro("bairro 1");
        e.setCidade("cidade 2");
        e.setComplemento("complemento 3");
        e.setEstado("estado 4");
        e.setLogradouro("logradouro 5");
        e.setNumero(6);
        enderecorepository.save(e);

        Usuario u = new Usuario();
        u.setTelefone("7777");
        u.setSenha("arthur123");
        u.setEndereco(e);
        u.setEmail("email 1");
        u.setCpf("8887553");
        u.setCnh("4414661");
        u.setNome("Arthur dos Santos Almeida");
        u.setPerfil("cliente");
        usuariorepository.save(u);

        Carro c = new Carro();
        c.setUsuario(u);
        c.setTipoCombustivel("tipo de combustivel 1");
        c.setPlaca("AEG-1204");
        c.setMarca("marca 2");
        c.setKmsRodados(3);
        c.setDocumentoCarro("documento do carro 4");
        c.setModelo("modelo 5");
        carrorepository.save(c);
    }

    @Test
    public void AoBuscarCarro_RetornaCarro(){
        Optional<Carro> carro = carrorepository.findById(1l);
        Assert.assertEquals(carro.get().getModelo(), "modelo 5");
    }

    @Test
    public void AoAtualizarCarro_RetornaCarroAtualizado(){
        Optional<Carro> carro = carrorepository.findById(1l);
        carro.get().setModelo("GOL BOLA");
        carrorepository.save(carro.get());
        Assert.assertEquals(carro.get().getModelo(), "GOL BOLA");
    }

    @Test
    public void AoDeletarCarro_RetornaVazio(){
        Optional<Carro> carro = carrorepository.findById(1l);
        carrorepository.delete(carro.get());
        carro = carrorepository.findById(1l);
        Assert.assertEquals(carro.isPresent(), false);
    }
}
