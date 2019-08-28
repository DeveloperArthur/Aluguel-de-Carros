package br.com.aluguel.de.carros.unidade.endereco;

import br.com.aluguel.de.carros.endereco.Endereco;
import br.com.aluguel.de.carros.endereco.EnderecoRepository;
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
public class TestaEnderecoRepository {
    @Autowired
    EnderecoRepository enderecorepository;

    @Before
    public void setup(){
        Endereco e = new Endereco();
        e.setNumero(1);
        e.setLogradouro("logradouro 2");
        e.setEstado("estado 3");
        e.setComplemento("complemento 4");
        e.setCidade("cidade 5");
        e.setBairro("bairro 6");
        enderecorepository.save(e);
    }

    @Test
    public void AoBuscarEndereco_RetornaEndereco(){
        Optional<Endereco> endereco = enderecorepository.findById(1l);
        Assert.assertEquals(endereco.get().getBairro(), "bairro 6");
    }

    @Test
    public void AoAtualizarEndereco_RetornaEnderecoAtualizado(){
        Optional<Endereco> endereco = enderecorepository.findById(1l);
        endereco.get().setComplemento("testando atualizacao");
        endereco = enderecorepository.findById(1l);
        Assert.assertEquals(endereco.get().getComplemento(), "testando atualizacao");
    }

    @Test
    public void AoDeletarEndereco_RetornaVazio(){
        Optional<Endereco> endereco = enderecorepository.findById(1l);
        enderecorepository.delete(endereco.get());
        endereco = enderecorepository.findById(1l);
        Assert.assertEquals(endereco.isPresent(), false);
    }
}
