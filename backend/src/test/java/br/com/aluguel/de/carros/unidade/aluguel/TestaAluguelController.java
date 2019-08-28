//package br.com.aluguel.de.carros.unidade.aluguel;
//
//import br.com.aluguel.de.carros.aluguel.Aluguel;
//import br.com.aluguel.de.carros.aluguel.AluguelService;
//import org.hamcrest.core.Is;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TestaAluguelController {
//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    AluguelService service;
//
//    @Test
//    public void testaUrlAluguel() throws Exception {
//        Aluguel al = new Aluguel();
//        al.setId(1l);
//        al.setValor(5);
//        given(service.aluguel(1l)).willReturn(Optional.of(al));
//        mvc.perform(get("/aluguel/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", Is.is(al.getId())));
//    }
//}
//
//
