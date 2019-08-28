package br.com.aluguel.de.carros.usuario;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, GerenciarTransacao<Usuario> {
    Optional<Usuario> findByEmail(String email);
}
