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
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setComplemento(endereco.getComplemento());
        dto.setEstado(endereco.getEstado());
        dto.setNumero(endereco.getNumero());
        return dto;
    }

    public static Endereco converteDtoEmEntidade(EnderecoDto dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setEstado(dto.getEstado());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
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
