package br.com.devthiagoramon.restwithspringbootandjavaerutio.unittests.mapper;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapperTest {

    PersonMapperTest INSTANCE = Mappers.getMapper(PersonMapperTest.class);

    @Mapping(target = ".", source = ".")
    PersonDTO personToPersonDTO(Person person);

    @Mapping(target = ".", source = ".")
    Person personDTOTOPerson(PersonDTO personDTO);

    @Mapping(target = ".", source = ".")
    List<PersonDTO> listPersonToDTOPerson(List<Person> personList);

}
