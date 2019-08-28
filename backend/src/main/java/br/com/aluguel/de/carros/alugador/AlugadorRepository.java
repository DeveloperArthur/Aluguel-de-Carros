package br.com.aluguel.de.carros.alugador;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlugadorRepository extends JpaRepository<Alugador, Long>, GerenciarTransacao<Alugador> {
}
