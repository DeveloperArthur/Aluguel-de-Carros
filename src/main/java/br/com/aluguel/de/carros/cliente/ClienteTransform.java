package br.com.aluguel.de.carros.cliente;

import java.util.ArrayList;
import java.util.List;

public final class ClienteTransform {

    public ClienteTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static ClienteDto converteEntidadeEmDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCnh(cliente.getCnh());
        dto.setCpf(cliente.getCpf());
        dto.setEmail(cliente.getEmail());
        dto.setEndereco(cliente.getEndereco());
        dto.setTelefone(cliente.getTelefone());
        return dto;
    }

    public static Cliente converteDtoEmEntidade(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setCnh(dto.getCnh());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setTelefone(dto.getTelefone());
        return cliente;
    }

    public static List<ClienteDto> converteEntidadesEmDtos(List<Cliente> clientes) {
        List<ClienteDto> listaDto = new ArrayList<>();
        for (Cliente cliente : clientes) {
            ClienteDto clienteDto = converteEntidadeEmDto(cliente);
            listaDto.add(clienteDto);
        }
        return listaDto;
    }
}