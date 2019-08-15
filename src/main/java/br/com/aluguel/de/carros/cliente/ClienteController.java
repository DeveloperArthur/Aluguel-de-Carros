package br.com.aluguel.de.carros.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Cliente> todos = service.todos();
        List<ClienteDto> listaDto = ClienteTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<ClienteDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> cliente(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = service.cliente(id);
        if (clienteOpt.isPresent()) {
            ClienteDto dto = ClienteTransform.converteEntidadeEmDto(clienteOpt.get());
            return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody ClienteDto dto) {
        Cliente cliente = ClienteTransform.converteDtoEmEntidade(dto);
        Cliente clienteSalvo = service.novo(cliente);
        dto = ClienteTransform.converteEntidadeEmDto(clienteSalvo);
        return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody ClienteDto dto) throws InterruptedException {
        Cliente cliente = ClienteTransform.converteDtoEmEntidade(dto);
        Cliente clienteAtualizado = service.atualiza(cliente);
        dto = ClienteTransform.converteEntidadeEmDto(clienteAtualizado);
        return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean clienteRemovido = service.deleta(id);
        if (clienteRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}