package br.com.aluguel.de.carros.alugador;


import java.util.ArrayList;
import java.util.List;

public class AlugadorTransform {
    public AlugadorTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static AlugadorDto converteEntidadeEmDto(Alugador cliente) {
        AlugadorDto dto = new AlugadorDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        return dto;
    }

    public static Alugador converteDtoEmEntidade(AlugadorDto dto) {
        Alugador alugador = new Alugador();
        alugador.setId(dto.getId());
        alugador.setNome(dto.getNome());
        return alugador;
    }

    public static List<AlugadorDto> converteEntidadesEmDtos(List<Alugador> alugadores) {
        List<AlugadorDto> listaDto = new ArrayList<>();
        for (Alugador alugador : alugadores) {
            AlugadorDto alugadorDto = converteEntidadeEmDto(alugador);
            listaDto.add(alugadorDto);
        }
        return listaDto;
    }
}
