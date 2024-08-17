package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v2.PersonDTOV2;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.services.PersonService;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    public PersonService personService;

    @GetMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_YML,
                            MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML})
    public PersonDTO findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_YML,
                                        MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML,})
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_YML,
                             MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML},
                 consumes = {MediaType.APPLICATION_YML,
                             MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML})
    public PersonDTO create(@RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PostMapping(value = "/v2", produces = {MediaType.APPLICATION_YML,
                                            MediaType.APPLICATION_JSON,
                                            MediaType.APPLICATION_XML},
                 consumes = {MediaType.APPLICATION_YML,
                             MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML})
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
        return personService.createV2(person);
    }

    @PutMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_YML,
                            MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML},
                consumes = {MediaType.APPLICATION_YML,
                            MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML})
    public PersonDTO update(@PathVariable("id") Long id,
                            @RequestBody PersonDTO person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }


}