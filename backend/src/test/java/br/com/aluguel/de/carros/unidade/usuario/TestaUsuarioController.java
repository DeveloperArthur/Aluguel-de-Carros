//package br.com.aluguel.de.carros.unidade.usuario;
//
//import br.com.aluguel.de.carros.endereco.Endereco;
//import br.com.aluguel.de.carros.usuario.Usuario;
//import br.com.aluguel.de.carros.usuario.UsuarioService;
//import org.hamcrest.core.Is;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class TestaUsuarioController {
//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    UsuarioService service;
//
//    @Test
//    public void testaUrlUsuario() throws Exception{
//        Endereco e = new Endereco();
//        e.setComplemento("complemento 1");
//        e.setId(1l);
//        e.setBairro("bairro 2");
//        e.setCidade("cidade 3");
//        e.setEstado("estado 4");
//        e.setLogradouro("logradouro 5");
//        e.setNumero(6);
//
//        Usuario u = new Usuario();
//        u.setPerfil("cliente");
//        u.setId(1l);
//        u.setNome("Arthur");
//        u.setCnh("000");
//        u.setCpf("111");
//        u.setEmail("teste@gmail.com");
//        u.setEndereco(e);
//        u.setSenha("senha 2");
//        u.setTelefone("4678612");
//
//        given(service.usuario(1l)).willReturn(Optional.of(u));
//        mvc.perform(get("/usuario").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.u", Is.is(u.getPerfil())));
//    }
//}
