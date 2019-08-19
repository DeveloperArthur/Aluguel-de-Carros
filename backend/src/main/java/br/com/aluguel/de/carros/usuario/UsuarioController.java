package br.com.aluguel.de.carros.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Usuario> todos = service.todos();
        List<UsuarioDto> listaDto = UsuarioTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<UsuarioDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> usuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = service.usuario(id);
        if (usuarioOpt.isPresent()) {
            UsuarioDto dto = UsuarioTransform.converteEntidadeEmDto(usuarioOpt.get());
            return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        Optional<Usuario> usuarioOpt = service.buscaUsuarioPorEmail(usuarioLoginDto.getEmail());
        if (usuarioOpt.isPresent()) {
            UsuarioDto dto = UsuarioTransform.converteEntidadeEmDto(usuarioOpt.get());
            if (service.validaAcessoDoUsuario(usuarioOpt.get(), usuarioLoginDto.getSenha()))
                return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);
            else
                return new ResponseEntity<UsuarioDto>(dto, HttpStatus.UNAUTHORIZED);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody UsuarioDto dto) {
        Usuario usuario = UsuarioTransform.converteDtoEmEntidade(dto);
        Usuario usuarioSalvo = service.novo(usuario);
        dto = UsuarioTransform.converteEntidadeEmDto(usuarioSalvo);
        return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody UsuarioDto dto) throws InterruptedException {
        Usuario usuario = UsuarioTransform.converteDtoEmEntidade(dto);
        Usuario usuarioAtualizado = service.atualiza(usuario);
        dto = UsuarioTransform.converteEntidadeEmDto(usuarioAtualizado);
        return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean usuarioRemovido = service.deleta(id);
        if (usuarioRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
