package br.com.aluguel.de.carros.carro;

import java.util.ArrayList;
import java.util.List;

public class CarroTransform {
    public CarroTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static CarroDto converteEntidadeEmDto(Carro carro) {
        CarroDto dto = new CarroDto();
        dto.setId(carro.getId());
        dto.setModelo(carro.getModelo());
        return dto;
    }

    public static Carro converteDtoEmEntidade(CarroDto dto) {
        Carro carro = new Carro();
        carro.setId(dto.getId());
        carro.setModelo(dto.getModelo());
        return carro;
    }

    public static List<CarroDto> converteEntidadesEmDtos(List<Carro> carros) {
        List<CarroDto> listaDto = new ArrayList<>();
        for (Carro carro : carros) {
            CarroDto carroDto = converteEntidadeEmDto(carro);
            listaDto.add(carroDto);
        }
        return listaDto;
    }
}
