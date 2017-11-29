package br.com.guilherme.campanha.services;

import br.com.guilherme.campanha.entity.Time;
import br.com.guilherme.campanha.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/time")
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Time> getTime() {
        return timeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Time addTime(@RequestBody Time time) {
        Time t = timeRepository.findByNome(time.getNome());
        if (t != null) {
            time = t;
        } else {
            timeRepository.save(time);
        }
        return time;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{timeId}")
    public Time deleteTime(@PathVariable Long timeId) {
        Time time = timeRepository.findById(timeId).get();
        timeRepository.deleteById(timeId);
        return time;
    }

}
