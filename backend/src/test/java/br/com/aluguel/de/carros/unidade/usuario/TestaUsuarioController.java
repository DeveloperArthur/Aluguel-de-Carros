package br.com.aluguel.de.carros.unidade.usuario;

import br.com.aluguel.de.carros.usuario.Usuario;
import br.com.aluguel.de.carros.usuario.UsuarioService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestaUsuarioController {
    @Autowired
    MockMvc mvc;

    @Autowired
    UsuarioService service;

    @Test
    public void testaUrlUsuario() throws Exception{
        Usuario u = new Usuario();
        u.setPerfil("cliente");
        given(service.usuario(1l)).willReturn(Optional.of(u));
        mvc.perform(get("/usuario").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.u", Is.is(u.getPerfil())));
    }
}
