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
        return dto;
    }

    public static Aluguel converteDtoEmEntidade(AluguelDto dto) {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(dto.getId());
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
