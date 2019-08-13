package br.com.aluguel.de.carros.alugador;


import java.util.ArrayList;
import java.util.List;

public class AlugadorTransform {
    public AlugadorTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static AlugadorDto converteEntidadeEmDto(Alugador alugador) {
        AlugadorDto dto = new AlugadorDto();
        dto.setId(alugador.getId());
        dto.setNome(alugador.getNome());
        dto.setCpf(alugador.getCpf());
        dto.setEmail(alugador.getEmail());
        dto.setEndereco(alugador.getEndereco());
        dto.setTelefone(alugador.getTelefone());
        return dto;
    }

    public static Alugador converteDtoEmEntidade(AlugadorDto dto) {
        Alugador alugador = new Alugador();
        alugador.setId(dto.getId());
        alugador.setNome(dto.getNome());
        alugador.setCpf(dto.getCpf());
        alugador.setEmail(dto.getEmail());
        alugador.setEndereco(dto.getEndereco());
        alugador.setTelefone(dto.getTelefone());
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
