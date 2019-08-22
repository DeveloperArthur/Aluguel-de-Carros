package br.com.aluguel.de.carros.aluguel;

import br.com.aluguel.de.carros.carro.CarroService;
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

    @Autowired
    private CarroService carroService;

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
    public ResponseEntity<?> salva(@RequestBody AluguelSalvaDto dto) {
        Aluguel aluguel = AluguelTransform.converteDtoEmEntidade(dto);
        service.novo(aluguel);
        return new ResponseEntity<AluguelSalvaDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody AluguelSalvaDto dto) {
        Aluguel aluguel = AluguelTransform.converteDtoEmEntidade(dto);
        service.atualiza(aluguel);
        return new ResponseEntity<AluguelSalvaDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean aluguelRemovido = service.deleta(id);
        if (aluguelRemovido)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/usuario/{idCliente}")
    public ResponseEntity<?> buscaAluguelPorIdDoUsuarioCliente(@PathVariable Long idCliente) {
        List<Aluguel> aluguelPorUsuario = service.buscaAluguelPorIdDoUsuarioCliente(idCliente);
        if (aluguelPorUsuario.isEmpty()) {
            return new ResponseEntity<>(aluguelPorUsuario, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aluguelPorUsuario, HttpStatus.OK);
    }
}