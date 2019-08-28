package br.com.aluguel.de.carros.unidade.aluguel;

import br.com.aluguel.de.carros.aluguel.Aluguel;
import br.com.aluguel.de.carros.aluguel.AluguelRepository;
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

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class TestaAluguelRepository {

    @Autowired
    EnderecoRepository enderecorepository;

    @Autowired
    UsuarioRepository usuariorepository;

    @Autowired
    AluguelRepository aluguelrepository;

    @Before
    public void setup(){
        Endereco e = new Endereco();
        e.setNumero(8);
        e.setLogradouro("teste");
        e.setEstado("teste");
        e.setComplemento("vai");
        e.setCidade("bomdia");
        e.setBairro("rua teste de almeida");
        enderecorepository.save(e);

        Usuario u = new Usuario();
        u.setPerfil("cliente");
        u.setNome("Thiago");
        u.setCnh("99999");
        u.setCpf("8888");
        u.setEmail("thiago@multiedro.com");
        u.setEndereco(e);
        u.setSenha("77777");
        u.setTelefone("222222-22");
        usuariorepository.save(u);

        LocalDate retirada = LocalDate.now();
        LocalDate entrega = LocalDate.now();

        Aluguel al = new Aluguel();
        al.setValor((float) 20.0);
        al.setRetirada(retirada);
        al.setEntrega(entrega);
        al.setUsuarioCliente(u);
        aluguelrepository.save(al);
    }

    @Test
    public void AoBuscarAluguel_RetornarAlguelCriado(){
        Optional<Aluguel> aluguel = aluguelrepository.findById(1l);
        Assert.assertEquals(aluguel.get().getValor() == 20.0, true);
    }

    @Test
    public void AoAtualizarAluguel_RetornaAluguelAtualizado(){
        Optional<Aluguel> aluguel = aluguelrepository.findById(1l);
        aluguel.get().setValor((float) 14.50);
        aluguelrepository.save(aluguel.get());
        Assert.assertEquals(aluguel.get().getValor() == 14.50, true);
    }

    @Test
    public void AoDeletarAluguel_RetornaVazio(){
        Optional<Aluguel> aluguel = aluguelrepository.findById(1l);
        aluguelrepository.delete(aluguel.get());
        aluguel = aluguelrepository.findById(1l);
        Assert.assertEquals(aluguel.isPresent(), false);
        System.out.println(aluguel);
    }


}
