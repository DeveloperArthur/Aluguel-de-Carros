package br.com.aluguel.de.carros.alugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlugadorServiceImpl implements AlugadorService {
    private AlugadorRepository repository;

    @Autowired
    public AlugadorServiceImpl(AlugadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Alugador> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Alugador> alugador(Long id) {
        return repository.findById(id);
    }

    @Override
    public Alugador novo(Alugador alugador) {
        alugador.setNome(alugador.getNome());
        return repository.save(alugador);
    }

    @Override
    public Alugador atualiza(Alugador alugador) {
        Alugador alugadorAtualizado = repository.save(alugador);
        //repository.reflesh(alugadorAtualizado);
        return alugadorAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Alugador> alugadorOpt = alugador(id);
        if (alugadorOpt.isPresent()) {
            repository.delete(alugadorOpt.get());
            return true;
        }
        return false;
    }
}
