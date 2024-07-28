package one.digitalinnovation.desafio.service;

import one.digitalinnovation.desafio.model.Prioridade;
import one.digitalinnovation.desafio.model.Tarefa;

import java.time.LocalDate;

public interface TarefaService {

    void salvar(Tarefa tarefa);
    void atualizar(Long id, Tarefa tarefa);
    void excluirPorId(Long id);
    Tarefa buscarPorId(Long id);
    Iterable<Tarefa> buscarTodas();
    Iterable<Tarefa> buscarPorPrioridade(Prioridade prioridade);
    Iterable<Tarefa> buscarPorDataVencimento(LocalDate dataVencimento);
}