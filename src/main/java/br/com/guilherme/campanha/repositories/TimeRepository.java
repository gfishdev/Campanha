package br.com.guilherme.campanha.repositories;

import br.com.guilherme.campanha.entity.Time;
import org.springframework.data.repository.CrudRepository;

public interface TimeRepository extends CrudRepository<Time, Long> {
    Time findByNome(String nome);
}
