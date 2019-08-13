package br.com.aluguel.de.carros.aluguel;

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
        dto.setIdAlugador(aluguel.getIdAlugador());
        dto.setIdCliente(aluguel.getIdCliente());
        dto.setRetirada(aluguel.getRetirada());
        dto.setValor(aluguel.getValor());
        return dto;
    }

    public static Aluguel converteDtoEmEntidade(AluguelDto dto) {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(dto.getId());
        aluguel.setEntrega(dto.getEntrega());
        aluguel.setIdAlugador(dto.getIdAlugador());
        aluguel.setRetirada(dto.getRetirada());
        aluguel.setValor(dto.getValor());
        aluguel.setIdCliente(dto.getIdCliente());
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
