package one.digitalinnovation.desafio.service;

import jakarta.validation.ValidationException;
import one.digitalinnovation.desafio.model.Prioridade;
import one.digitalinnovation.desafio.model.Tarefa;
import one.digitalinnovation.desafio.repository.TarefaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void salvar(Tarefa tarefa) {
        validarTarefa(tarefa);
        tarefaRepository.save(tarefa);
    }

    @Override
    public void atualizar(Long id, Tarefa tarefa) {
        Optional<Tarefa> tarefaDb = tarefaRepository.findById(id);
        if (tarefaDb.isPresent()) {
            validarTarefa(tarefa);
            tarefaRepository.save(tarefa);
        }
    }

    @Override
    public void excluirPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

    @Override
    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @Override
    public Iterable<Tarefa> buscarTodas() {
        return tarefaRepository.findAll();
    }

    @Override
    public List<Tarefa> buscarPorPrioridade(Prioridade prioridade) {
        return tarefaRepository.findByPrioridade(prioridade);
    }

    @Override
    public List<Tarefa> buscarPorDataVencimento(LocalDate dataVencimento) {
        return tarefaRepository.findByDataVencimento(dataVencimento);
    }

    private void validarTarefa(Tarefa tarefa) {
        if (tarefa.getDataVencimento() != null && tarefa.getDataVencimento().isBefore(LocalDate.now())) {
            throw new ValidationException("A data de vencimento deve ser no futuro ou no presente.");
        }
        if (tarefa.getTitulo().trim().isEmpty()) {
            throw new ValidationException("O titulo não pode ser nulo");
        }
        if (tarefa.getDescricao().trim().isEmpty()) {
            throw new ValidationException("A descrição não pode ser nula");
        }
    }
}