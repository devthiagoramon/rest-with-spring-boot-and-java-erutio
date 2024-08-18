package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v2.PersonDTOV2;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.services.PersonService;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @CrossOrigin("*")
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing persons")
public class PersonController {

    @Autowired
    public PersonService personService;


    @Operation(summary = "Find by id the user", tags = {"People"})
    @GetMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_YML,
                            MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML})
    public PersonDTO findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @Operation(summary = "Find all users", tags = {"People"})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_YML,
                                        MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML,})
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }


    @Operation(summary = "Create a user", tags = {"People"})
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

    @Operation(summary = "Update user by id", tags = {"People"})
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

    @Operation(summary = "Delete a user by id", tags = {"People"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }


}