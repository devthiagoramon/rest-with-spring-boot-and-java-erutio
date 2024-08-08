package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.ResourceNotFoundException;
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

    public List<Person> findAll() {
        logger.info("Finding all persons!");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
    }

    public Person create(Person person) {
        logger.info("Creating person!");
        return repository.save(person);
    }

    public Person update(Long id, Person personUpdated) {
        logger.info("Update person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        person.setFirstName(personUpdated.getFirstName());
        person.setLastName(personUpdated.getLastName());
        person.setAddress(personUpdated.getAddress());
        person.setGender(personUpdated.getGender());
        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Delete person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        repository.delete(person);
    }


}
