package one.digitalinnovation.desafio.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovation.desafio.model.Prioridade;
import one.digitalinnovation.desafio.model.Tarefa;
import one.digitalinnovation.desafio.service.TarefaService;

import java.time.LocalDate;

@RestController
@RequestMapping("/tarefas")
public class TarefaRestController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> buscarTodas() {

        return ResponseEntity.ok(tarefaService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @GetMapping("/filtrar/prioridade/{prioridade}")
    public ResponseEntity<Iterable<Tarefa>> buscarPorPrioridade(@PathVariable Prioridade prioridade) {
        return ResponseEntity.ok(tarefaService.buscarPorPrioridade(prioridade));
    }

    @GetMapping("/filtrar/dataVencimento/{dataVencimento}")
    public ResponseEntity<Iterable<Tarefa>> buscarPorDataVencimento(@PathVariable LocalDate dataVencimento) {
        return ResponseEntity.ok(tarefaService.buscarPorDataVencimento(dataVencimento));
    }

    @PostMapping
    public ResponseEntity<Tarefa> salvar(@RequestBody Tarefa tarefa) {
        tarefaService.salvar(tarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        System.out.println("Atualizando tarefa: " + tarefa);
        tarefaService.atualizar(id, tarefa);
        Tarefa tarefaAtualizada = tarefaService.buscarPorId(id);
        System.out.println("Tarefa atualizada: " + tarefaAtualizada);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        tarefaService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
}