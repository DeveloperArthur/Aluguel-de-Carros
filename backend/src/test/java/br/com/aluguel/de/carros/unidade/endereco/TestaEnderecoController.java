package br.com.aluguel.de.carros.unidade.endereco;

import br.com.aluguel.de.carros.endereco.Endereco;
import br.com.aluguel.de.carros.endereco.EnderecoService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestaEnderecoController {
    @Autowired
    MockMvc mvc;

    @Autowired
    EnderecoService service;

    @Test
    public void testaUrlEndereco() throws Exception{
        Endereco e = new Endereco();
        e.setComplemento("teste complemento");
        given(service.endereco(1l)).willReturn(Optional.of(e));
        mvc.perform(get("/endereco").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.e", Is.is(e.getComplemento())));
    }
}
