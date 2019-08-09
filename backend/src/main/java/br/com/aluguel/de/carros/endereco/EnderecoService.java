package br.com.aluguel.de.carros.endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {
    List<Endereco> todos();

    Optional<Endereco> endereco(Long id);

    Endereco novo(Endereco endereco);

    Endereco atualiza(Endereco endereco);

    boolean deleta(Long id);
}
