package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long>, GerenciarTransacao<Aluguel> {
    List<Aluguel> findByUsuarioClienteId(Long idCliente);
}
