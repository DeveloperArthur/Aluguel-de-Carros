package br.com.aluguel.de.carros.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository repository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> cliente(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente novo(Cliente cliente) {
//        cliente.setNome(cliente.getNome());
        return repository.save(cliente);
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        Cliente clienteAtualizado = repository.save(cliente);
//      repository.reflesh(clienteAtualizado);
        return clienteAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Cliente> clienteOpt = cliente(id);
        if (clienteOpt.isPresent()) {
            repository.delete(clienteOpt.get());
            return true;
        }
        return false;
    }
}