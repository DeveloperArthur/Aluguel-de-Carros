package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.carro.Carro;
import br.com.aluguel.de.carros.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AluguelTransform {
    public AluguelTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static AluguelDto converteEntidadeEmDto(Aluguel aluguel) {
        AluguelDto dto = new AluguelDto();
        dto.setId(aluguel.getId());
        dto.setEntrega(aluguel.getEntrega());
        dto.setCarro(aluguel.getCarro());
        dto.setUsuarioCliente(aluguel.getUsuarioCliente());
        dto.setRetirada(aluguel.getRetirada());
        dto.setValor(aluguel.getValor());
        return dto;
    }

    public static Aluguel converteDtoEmEntidade(AluguelDto dto) {
        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(dto.getCarro());
        aluguel.setId(dto.getId());
        aluguel.setEntrega(dto.getEntrega());
        aluguel.setRetirada(dto.getRetirada());
        aluguel.setValor(dto.getValor());
        aluguel.setUsuarioCliente(dto.getUsuarioCliente());
        return aluguel;
    }

    public static Aluguel converteDtoEmEntidade(AluguelSalvaDto dto) {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(dto.getId());
        aluguel.setEntrega(dto.getEntrega());
        aluguel.setRetirada(dto.getRetirada());
        aluguel.setValor(dto.getValor());
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioClienteId());
        aluguel.setUsuarioCliente(usuario);
        Carro carro = new Carro();
        carro.setId(dto.getCarroId());
        aluguel.setCarro(carro);
        return aluguel;
    }

    public static List<AluguelDto> converteEntidadesEmDtos(List<Aluguel> aluguels) {
        List<AluguelDto> listaDto = new ArrayList<>();
        for (Aluguel aluguel : aluguels) {
            AluguelDto aluguelDto = converteEntidadeEmDto(aluguel);
            listaDto.add(aluguelDto);
        }
        return listaDto;
    }
}
