package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.UnsopportedMathOperationException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private Person mockPerson(long id) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Vasco " + id);
        person.setLastName(("Da gama " + id));
        person.setAddress("Manaus " + id);
        person.setGender("Sexo " + id);
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Vasco");
        person.setLastName(("Da gama"));
        person.setAddress("Manaus");
        person.setGender("Sexo");
        return person;
    }

}
