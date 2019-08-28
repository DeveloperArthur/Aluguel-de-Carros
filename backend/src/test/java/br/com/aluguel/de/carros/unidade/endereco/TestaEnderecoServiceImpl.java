package br.com.aluguel.de.carros.unidade.endereco;

import br.com.aluguel.de.carros.endereco.Endereco;
import br.com.aluguel.de.carros.endereco.EnderecoRepository;
import br.com.aluguel.de.carros.endereco.EnderecoService;
import br.com.aluguel.de.carros.endereco.EnderecoServiceImpl;
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
@ContextConfiguration(classes = {EnderecoServiceImpl.class})
public class TestaEnderecoServiceImpl {

    @MockBean
    private EnderecoService service;

    @MockBean
    private EnderecoRepository repository;

    private Endereco endereco;

    @Before
    public void setup(){
        Endereco endereco = new Endereco();
        endereco.setId(1l);
        endereco.setComplemento("teste complemento");
        Optional<Endereco> enderecoOpt = Optional.of(endereco);
        Mockito.when(repository.findById(1l)).thenReturn(enderecoOpt);
        this.endereco = endereco;
    }

    @Test
    public void aoBuscarUmEnderecoExistente_RetornarUmEnderecoOptional(){
        Mockito.when(service.endereco(1l)).thenReturn(Optional.ofNullable(this.endereco));
        Optional<Endereco> resultado = service.endereco(1l);
        Endereco endereco = resultado.get();

        Assertions.assertThat(endereco.getId())
                .isEqualTo(1l);
    }
}
