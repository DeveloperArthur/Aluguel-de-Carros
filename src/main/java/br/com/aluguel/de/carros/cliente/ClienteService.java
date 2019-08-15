package br.com.aluguel.de.carros.cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> todos();

    Optional<Cliente> cliente(Long id);

    Cliente novo(Cliente cliente);

    Cliente atualiza(Cliente cliente);

    boolean deleta(Long id);
}