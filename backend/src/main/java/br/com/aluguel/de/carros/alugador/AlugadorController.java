package br.com.aluguel.de.carros.alugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/alugador")
public class AlugadorController {
    @Autowired
    private AlugadorService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Alugador> todos = service.todos();
        List<AlugadorDto> listaDto = AlugadorTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<AlugadorDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> alugador(@PathVariable Long id) {
        Optional<Alugador> alugadorOpt = service.alugador(id);
        if (alugadorOpt.isPresent()) {
            AlugadorDto dto = AlugadorTransform.converteEntidadeEmDto(alugadorOpt.get());
            return new ResponseEntity<AlugadorDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody AlugadorDto dto) {
        Alugador alugador = AlugadorTransform.converteDtoEmEntidade(dto);
        Alugador alugadorSalvo = service.novo(alugador);
        dto = AlugadorTransform.converteEntidadeEmDto(alugadorSalvo);
        return new ResponseEntity<AlugadorDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody AlugadorDto dto) throws InterruptedException {
        Alugador alugador = AlugadorTransform.converteDtoEmEntidade(dto);
        Alugador alugadorAtualizado = service.atualiza(alugador);
        dto = AlugadorTransform.converteEntidadeEmDto(alugadorAtualizado);
        return new ResponseEntity<AlugadorDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean alugadorRemovido = service.deleta(id);
        if (alugadorRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
