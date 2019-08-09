package br.com.aluguel.de.carros.aluguel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/aluguel")
public class AluguelController {
    @Autowired
    private AluguelService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Aluguel> todos = service.todos();
        List<AluguelDto> listaDto = AluguelTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<AluguelDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> aluguel(@PathVariable Long id) {
        Optional<Aluguel> aluguelOpt = service.aluguel(id);
        if (aluguelOpt.isPresent()) {
            AluguelDto dto = AluguelTransform.converteEntidadeEmDto(aluguelOpt.get());
            return new ResponseEntity<AluguelDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody AluguelDto dto) {
        Aluguel aluguel = AluguelTransform.converteDtoEmEntidade(dto);
        Aluguel aluguelSalvo = service.novo(aluguel);
        dto = AluguelTransform.converteEntidadeEmDto(aluguelSalvo);
        return new ResponseEntity<AluguelDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody AluguelDto dto) throws InterruptedException {
        Aluguel aluguel = AluguelTransform.converteDtoEmEntidade(dto);
        Aluguel aluguelAtualizado = service.atualiza(aluguel);
        dto = AluguelTransform.converteEntidadeEmDto(aluguelAtualizado);
        return new ResponseEntity<AluguelDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean aluguelRemovido = service.deleta(id);
        if (aluguelRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
