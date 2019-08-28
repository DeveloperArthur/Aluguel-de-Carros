package br.com.aluguel.de.carros.unidade.aluguel;


import br.com.aluguel.de.carros.aluguel.Aluguel;
import br.com.aluguel.de.carros.aluguel.AluguelRepository;
import br.com.aluguel.de.carros.aluguel.AluguelService;
import br.com.aluguel.de.carros.aluguel.AluguelServiceImpl;
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
@ContextConfiguration(classes = {AluguelServiceImpl.class})
public class TestaAluguelServiceImpl {

    @MockBean
    private AluguelService service;

    @MockBean
    private AluguelRepository repository;

    private Aluguel aluguel;

    @Before
    public void setup() {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(1L);
        aluguel.setValor(33);
        Optional<Aluguel> aluguelOpt = Optional.of(aluguel);
        Mockito.when(repository.findById(1L))
                .thenReturn(aluguelOpt);
        this.aluguel = aluguel;
    }

    @Test
    public void aoBuscarUmAluguelExistente_RetornarUmAluguelOptional() {
        Mockito.when(service.aluguel(1L))
                .thenReturn(Optional.ofNullable(this.aluguel));
        Optional<Aluguel> resultado = service.aluguel(1L);
        Aluguel aluguel = resultado.get();

        Assertions.assertThat(aluguel.getId())
                .isEqualTo(1L);
    }
}
