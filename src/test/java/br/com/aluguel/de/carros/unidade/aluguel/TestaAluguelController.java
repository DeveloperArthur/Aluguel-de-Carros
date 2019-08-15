package br.com.aluguel.de.carros.unidade.aluguel;

import org.junit.Test;

public class TestaAluguelController {
    @Test
    public 
}

    @Test
//@WithMockUser(username = "adm-app-catho@catho.com", authorities = {"ADMIN"})
    public void aoBuscarUmCentroDeCustoRetornarUmJsonComPropriedadeCentroDeCusto() throws Exception {
        CentroDeCusto centroDeCusto = new CentroDeCusto();
        centroDeCusto.setNome("Financeiro");


        given(service.centroDeCusto(1L)).willReturn(Optional.of(centroDeCusto));

        mvc.perform(get("/centrosdecustos/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.centroDeCusto", Is.is(centroDeCusto.getCentroDeCusto())));
    }