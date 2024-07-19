package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.UnsopportedMathOperationException;
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
    public Person findById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @GetMapping
    public List<Person> findAll(){
        return personService.findAll();
    }

}