package br.com.aluguel.de.carros.carro;

import java.util.List;
import java.util.Optional;

public interface CarroService {
    List<Carro> todos();

    Optional<Carro> carro(Long id);

    Carro novo(Carro carro);

    Carro atualiza(Carro carro);

    boolean deleta(Long id);

    List<Carro> buscaCarrosPorIdDoUsuarioRegistrador(Long idUsuario);

    void gerenciaStatusDoCarro(Long idCarro);

    void mudaStatusParaDisponivel(Carro carro);

    //List<Carro> buscaCarrosAlugadosPorIdDoUsuarioRegistrador(Long idUsuario, boolean estaAlugado);

}
