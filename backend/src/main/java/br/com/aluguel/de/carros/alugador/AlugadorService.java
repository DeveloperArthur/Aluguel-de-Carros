package br.com.aluguel.de.carros.alugador;

import java.util.List;
import java.util.Optional;

public interface AlugadorService {
    List<Alugador> todos();

    Optional<Alugador> alugador(Long id);

    Alugador novo(Alugador alugador);

    Alugador atualiza(Alugador alugador);

    boolean deleta(Long id);
}
