package br.com.guilherme.campanha.repositories;

import br.com.guilherme.campanha.entity.Campanha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CampanhaRepository extends CrudRepository<Campanha, Long> {
    @Query("select c from Campanha c where (c.vigenciaInicio between ?1 and ?2) or (c.vigenciaFim between ?1 and ?2)")
    List<Campanha> findByInEffect(LocalDate dateStart, LocalDate dateEnd);

    List<Campanha> findByVigenciaFim(LocalDate dateEnd);

    @Query("select c from Campanha c where time.id = ?1")
    List<Campanha> findByTimeId(Long timeId);
}