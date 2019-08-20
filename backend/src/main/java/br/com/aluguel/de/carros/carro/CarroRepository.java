package br.com.aluguel.de.carros.carro;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>, GerenciarTransacao<Carro> {
    List<Carro> findByUsuarioRegistradorId(Long idUsuario);
}