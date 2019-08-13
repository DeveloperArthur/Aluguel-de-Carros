package br.com.aluguel.de.carros.endereco;

import br.com.aluguel.de.carros.geral.GerenciarTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, GerenciarTransacao<Endereco> {
}
