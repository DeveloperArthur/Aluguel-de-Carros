package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.carro.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelServiceImpl implements AluguelService {
    private AluguelRepository repository;

    @Autowired
    private CarroService carroService;

    @Autowired
    public AluguelServiceImpl(AluguelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Aluguel> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Aluguel> aluguel(Long id) {
        return repository.findById(id);
    }

    @Override
    public Aluguel novo(Aluguel aluguel) {
        aluguel.setEntrega(aluguel.getEntrega());
        carroService.gerenciaStatusDoCarro(aluguel.getCarro().getId());
        return repository.save(aluguel);
    }

    @Override
    public Aluguel atualiza(Aluguel aluguel) {
        Aluguel aluguelAtualizado = repository.save(aluguel);
        //repository.reflesh(aluguelAtualizado);
        return aluguelAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Aluguel> aluguelOpt = aluguel(id);
        if (aluguelOpt.isPresent()) {
            Aluguel aluguel = aluguelOpt.get();
            carroService.mudaStatusParaDisponivel(aluguel.getCarro());
            repository.delete(aluguelOpt.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Aluguel> buscaAluguelPorIdDoUsuarioCliente(Long idCliente) {
        return repository.findByUsuarioClienteId(idCliente);
    }

    @Override
    public List<Aluguel> buscaAluguelPorIdDoUsuarioRegistradorDoCarroAlugado(Long idUsuarioRegistrador){
        return repository.findByCarroUsuarioRegistradorId(idUsuarioRegistrador);
    }
}
