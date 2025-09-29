package br.senac.tads.dsw.gerenciador.tarefas;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/tarefas")
public class TarefaRestController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public List<TarefaDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<String> addNew(@RequestBody @Valid TarefaDTO input) {
        try {
            TarefaDTO tarefa = service.addNew(input);
            
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{title}")
                    .buildAndExpand(tarefa.getTitle())
                    .toUri();
            
            return ResponseEntity.created(location).body("Tarefa criada com sucesso!");

        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
}
