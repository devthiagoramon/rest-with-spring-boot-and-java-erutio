package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonVO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/person")
public class PersonController {

    @Autowired
    public PersonService personService;

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PutMapping("/{id}")
    public PersonVO update(@PathVariable("id") Long id,
                         @RequestBody PersonVO person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }


}