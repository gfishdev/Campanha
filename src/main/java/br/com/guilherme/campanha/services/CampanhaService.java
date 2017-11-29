package br.com.guilherme.campanha.services;


import br.com.guilherme.campanha.entity.Campanha;
import br.com.guilherme.campanha.repositories.CampanhaRepository;
import br.com.guilherme.campanha.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/campanha")
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    TimeRepository timeRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/{campanhaId}")
    public Campanha getCampanha(@PathVariable Long campanhaId) {
        Campanha campanha = campanhaRepository.findById(campanhaId).orElse(null);

        return campanha;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Stream<Campanha> getCampanha() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        return StreamSupport.stream(campanhaRepository.findAll().spliterator(), false)
                .filter(c -> LocalDate.now().isBefore(c.getVigenciaFim()) || LocalDate.now().isEqual(c.getVigenciaFim()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Campanha addCampanha(@RequestBody Campanha campanha) {
        if (campanha.getVigenciaInicio() == null) {
            campanha.setVigenciaInicio(LocalDate.now());
        }
        if (campanha.getVigenciaFim() == null) {
            campanha.setVigenciaFim(LocalDate.now());
        }
        List<Campanha> campanhasEmVigencia = campanhaRepository.findByInEffect(campanha.getVigenciaInicio(), campanha.getVigenciaFim());
        System.out.println(Arrays.toString(campanhasEmVigencia.toArray()));
        campanhasEmVigencia.forEach(this::addDayCampanha);
        return saveNewCampanha(campanha);
    }

    public void addDayCampanha(Campanha campanha) {
        List<Campanha> compareTo;
        do {
            campanha.setVigenciaFim(campanha.getVigenciaFim().plusDays(1));
            System.out.println(campanha.getVigenciaFim());
            compareTo = campanhaRepository.findByVigenciaFim(campanha.getVigenciaFim());
        }
        while(compareTo != null && !compareTo.isEmpty());
        campanhaRepository.save(campanha);
    }



    public Campanha saveNewCampanha(Campanha campanha) {
        List<Campanha> compareTo = campanhaRepository.findByVigenciaFim(campanha.getVigenciaFim());
        while(compareTo != null && !compareTo.isEmpty()) {
            campanha.setVigenciaFim(campanha.getVigenciaFim().plusDays(1));
            compareTo = campanhaRepository.findByVigenciaFim(campanha.getVigenciaFim());
        }

        campanhaRepository.save(campanha);

        campanha.setTime(timeRepository.findById(campanha.getTime().getId()).get());

        return campanha;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{campanhaId}")
    public Campanha deleteCampanha(@PathVariable Long campanhaId) {
        Campanha campanha = campanhaRepository.findById(campanhaId).orElse(null);
        if(campanha != null)
            campanhaRepository.deleteById(campanhaId);
        return campanha;
    }

}