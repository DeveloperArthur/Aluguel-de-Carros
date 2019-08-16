package br.com.aluguel.de.carros.unidade.carro;


import br.com.aluguel.de.carros.carro.Carro;
import br.com.aluguel.de.carros.carro.CarroService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestaCarroController {
    @Autowired
    MockMvc mvc;

    @Autowired
    CarroService service;

    @Test
    public void testaUrlAluguel() throws Exception{
        Carro c = new Carro();
        c.setModelo("golf 98");

        given(service.carro(1l)).willReturn(Optional.of(c));
        mvc.perform(get("/carro").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.c", Is.is(c.getModelo())));
    }
}
