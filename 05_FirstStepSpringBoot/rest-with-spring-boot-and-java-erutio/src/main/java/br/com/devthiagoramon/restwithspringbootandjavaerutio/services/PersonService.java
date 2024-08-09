package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonVO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.ResourceNotFoundException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.mapper.DozerMapper;
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

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        Person entity = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating person!");

        Person person = DozerMapper.parseObject(personVO, Person.class);
        Person save = repository.save(person);
        return DozerMapper.parseObject(save, PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO personUpdated) {
        logger.info("Update person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        person.setFirstName(personUpdated.getFirstName());
        person.setLastName(personUpdated.getLastName());
        person.setAddress(personUpdated.getAddress());
        person.setGender(personUpdated.getGender());
        Person editedEntity = repository.save(person);
        return DozerMapper.parseObject(editedEntity, PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Delete person id:" + id);
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Can't find the user by id"));
        repository.delete(person);
    }


}
