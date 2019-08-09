package br.com.aluguel.de.carros.endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoTransform {
    public EnderecoTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static EnderecoDto converteEntidadeEmDto(Endereco endereco) {
        EnderecoDto dto = new EnderecoDto();
        dto.setId(endereco.getId());
        dto.setLogradouro(endereco.getLogradouro());
        return dto;
    }

    public static Endereco converteDtoEmEntidade(EnderecoDto dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        dto.setLogradouro(endereco.getLogradouro());
        return endereco;
    }

    public static List<EnderecoDto> converteEntidadesEmDtos(List<Endereco> enderecos) {
        List<EnderecoDto> listaDto = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            EnderecoDto enderecoDto = converteEntidadeEmDto(endereco);
            listaDto.add(enderecoDto);
        }
        return listaDto;
    }
}
