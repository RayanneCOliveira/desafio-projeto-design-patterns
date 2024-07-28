package one.digitalinnovation.desafio.repository;

import one.digitalinnovation.desafio.model.Prioridade;
import one.digitalinnovation.desafio.model.Tarefa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, Long> {

    List<Tarefa> findByPrioridade(Prioridade prioridade);
    List<Tarefa> findByDataVencimento(LocalDate dataVencimento);
}
