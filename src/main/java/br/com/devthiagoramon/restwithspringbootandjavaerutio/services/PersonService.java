package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.ResourceNotFoundException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.mapper.PersonMapper;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return PersonMapper.INSTANCE.listPersonToDTOPerson(repository.findAll());
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person!");
        Person entity = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        return PersonMapper.INSTANCE.personToPersonDTO(entity);
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating person!");

        Person person = PersonMapper.INSTANCE.personDTOTOPerson(personDTO);
        Person save = repository.save(person);
        return PersonMapper.INSTANCE.personToPersonDTO(save);
    }

    public PersonDTO update(Long id, PersonDTO personUpdated) {
        logger.info("Update person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        person.setFirstName(personUpdated.getFirstName());
        person.setLastName(personUpdated.getLastName());
        person.setAddress(personUpdated.getAddress());
        person.setGender(personUpdated.getGender());
        Person editedEntity = repository.save(person);
        return PersonMapper.INSTANCE.personToPersonDTO(editedEntity);
    }

    public void delete(Long id) {
        logger.info("Delete person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        repository.delete(person);
    }


}
