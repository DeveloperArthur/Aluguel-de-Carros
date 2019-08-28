package br.com.aluguel.de.carros.aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelService {
    List<Aluguel> todos();

    Optional<Aluguel> aluguel(Long id);

    Aluguel novo(Aluguel aluguel);

    Aluguel atualiza(Aluguel aluguel);

    boolean deleta(Long id);
}
