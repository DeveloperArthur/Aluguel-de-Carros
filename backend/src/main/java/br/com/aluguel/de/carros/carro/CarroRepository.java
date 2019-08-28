package br.com.aluguel.de.carros.carro;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>, GerenciarTransacao<Carro> {
    List<Carro> findByUsuarioRegistradorId(Long idUsuario);
    Optional<Carro> findByPlaca(String placa);

    //List<Carro> findByUsuarioRegistradorIdAndEstaAlugado(Long idUsuario, boolean estaAlugado);
}