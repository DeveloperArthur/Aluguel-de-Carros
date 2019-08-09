package br.com.aluguel.de.carros.aluguel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelServiceImpl implements AluguelService{
    private AluguelRepository repository;

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
            repository.delete(aluguelOpt.get());
            return true;
        }
        return false;
    }
}
