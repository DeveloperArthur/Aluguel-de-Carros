package br.com.aluguel.de.carros.usuario;


import java.util.ArrayList;
import java.util.List;

public class UsuarioTransform {
    public UsuarioTransform() {
        throw new RuntimeException("Não é permitido instanciar esta classe! " + this.getClass().getSimpleName());
    }

    public static UsuarioDto converteEntidadeEmDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setCnh(usuario.getCnh());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setEndereco(usuario.getEndereco());
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setPerfil(usuario.getPerfil());
        dto.setSenha(usuario.getSenha());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }

    public static Usuario converteDtoEmEntidade(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        //alugador.setId(dto.getId());
        usuario.setCnh(dto.getCnh());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(dto.getEndereco());
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setPerfil(dto.getPerfil());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        return usuario;
    }

    public static List<UsuarioDto> converteEntidadesEmDtos(List<Usuario> usuarios) {
        List<UsuarioDto> listaDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDto usuarioDto = converteEntidadeEmDto(usuario);
            listaDto.add(usuarioDto);
        }
        return listaDto;
    }
}
