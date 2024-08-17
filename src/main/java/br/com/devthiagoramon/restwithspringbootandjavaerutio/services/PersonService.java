package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.controller.PersonController;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v2.PersonDTOV2;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.RequestObjectNullException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.ResourceNotFoundException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.mapper.PersonMapper;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private Person mockPerson(long id) {
        Person person = new Person();
        return person;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons!");
        var persons = PersonMapper.INSTANCE.listPersonToDTOPerson(repository.findAll());
        persons.forEach(person -> {
            person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        });
        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person!");
        Person entity = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        PersonDTO dto = PersonMapper.INSTANCE.personToPersonDTO(entity);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating person!");

        if (personDTO == null) throw new RequestObjectNullException();
        Person person = PersonMapper.INSTANCE.personDTOTOPerson(personDTO);
        Person save = repository.save(person);
        PersonDTO dto = PersonMapper.INSTANCE.personToPersonDTO(save);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 personDTOV2) {
        logger.info("Creating person!");
        Person person = PersonMapper.INSTANCE.personDTOV2toPerson(personDTOV2);
//        Person save = repository.save(person);
        return PersonMapper.INSTANCE.persontoPersonDTOV2(person);
    }

    public PersonDTO update(Long id, PersonDTO personUpdated) {
        logger.info("Update person id:" + id);
        if (personUpdated == null) throw new RequestObjectNullException();
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        person.setFirstName(personUpdated.getFirstName());
        person.setLastName(personUpdated.getLastName());
        person.setAddress(personUpdated.getAddress());
        person.setGender(personUpdated.getGender());
        Person editedEntity = repository.save(person);
        PersonDTO dto = PersonMapper.INSTANCE.personToPersonDTO(editedEntity);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public void delete(Long id) {
        logger.info("Delete person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        repository.delete(person);
    }

}
