package microstudy.com.agenda.api.controller;

import lombok.RequiredArgsConstructor;
import microstudy.com.agenda.api.request.AgendaRequest;
import microstudy.com.agenda.api.response.AgendaResponse;
import microstudy.com.agenda.domain.service.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos() {
        return ResponseEntity.ok(agendaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(Long id) {
        var agenda = agendaService.buscarPorId(id);
        if(!Objects.nonNull(agenda)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agenda);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest agendaRequest) {
        var agenda = agendaService.salvar(agendaRequest);
        if(!Objects.nonNull(agenda)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
