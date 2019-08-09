package br.com.aluguel.de.carros.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Endereco> todos = service.todos();
        List<EnderecoDto> listaDto = EnderecoTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<EnderecoDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> endereco(@PathVariable Long id) {
        Optional<Endereco> enderecoOpt = service.endereco(id);
        if (enderecoOpt.isPresent()) {
            EnderecoDto dto = EnderecoTransform.converteEntidadeEmDto(enderecoOpt.get());
            return new ResponseEntity<EnderecoDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody EnderecoDto dto) {
        Endereco endereco = EnderecoTransform.converteDtoEmEntidade(dto);
        Endereco enderecoSalvo = service.novo(endereco);
        dto = EnderecoTransform.converteEntidadeEmDto(enderecoSalvo);
        return new ResponseEntity<EnderecoDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody EnderecoDto dto) throws InterruptedException {
        Endereco endereco = EnderecoTransform.converteDtoEmEntidade(dto);
        Endereco enderecoAtualizado = service.atualiza(endereco);
        dto = EnderecoTransform.converteEntidadeEmDto(enderecoAtualizado);
        return new ResponseEntity<EnderecoDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean enderecoRemovido = service.deleta(id);
        if (enderecoRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
