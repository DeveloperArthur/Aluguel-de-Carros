package br.com.aluguel.de.carros.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    private EnderecoRepository repository;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Endereco> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Endereco> endereco(Long id) {
        return repository.findById(id);
    }

    @Override
    public Endereco novo(Endereco endereco) {
        endereco.setBairro(endereco.getBairro());
        return (Endereco) repository.save(endereco);
    }

    @Override
    public Endereco atualiza(Endereco endereco) {
        Endereco enderecoAtualizado = (Endereco) repository.save(endereco);
        //repository.reflesh(enderecoAtualizado);
        return enderecoAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Endereco> enderecoOpt = endereco(id);
        if (enderecoOpt.isPresent()) {
            repository.delete(enderecoOpt.get());
            return true;
        }
        return false;
    }
}
