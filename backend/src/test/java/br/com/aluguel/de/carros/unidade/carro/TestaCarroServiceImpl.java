package br.com.aluguel.de.carros.unidade.carro;

import br.com.aluguel.de.carros.carro.Carro;
import br.com.aluguel.de.carros.carro.CarroRepository;
import br.com.aluguel.de.carros.carro.CarroService;
import br.com.aluguel.de.carros.carro.CarroServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarroServiceImpl.class})
public class TestaCarroServiceImpl {
    @MockBean
    private CarroService service;

    @MockBean
    private CarroRepository repository;

    private Carro carro;

    @Before
    public void setup(){
        Carro carro = new Carro();
        carro.setId(1l);
        carro.setPlaca("ART-1234");
        Optional<Carro> carroOpt = Optional.of(carro);
        Mockito.when(repository.findById(1l)).thenReturn(carroOpt);
        this.carro = carro;
    }

    @Test
    public void aoBuscarUmCarroExistente_RetornarUmCarroOptional(){
        Mockito.when(service.carro(1l)).thenReturn(Optional.ofNullable(this.carro));
        Optional<Carro> resultado = service.carro(1l);
        Carro carro = resultado.get();

        Assertions.assertThat(carro.getId())
                .isEqualTo(1l);
    }
}
