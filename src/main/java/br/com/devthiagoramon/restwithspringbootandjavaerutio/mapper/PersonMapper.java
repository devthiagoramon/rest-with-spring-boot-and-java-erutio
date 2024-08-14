package br.com.devthiagoramon.restwithspringbootandjavaerutio.mapper;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v2.PersonDTOV2;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = ".", source = ".", ignore = true)
    PersonDTO personToPersonDTO(Person person);

    @Mapping(target = ".", source = ".", ignore = true)
    Person personDTOTOPerson(PersonDTO personDTO);

    @Mapping(target = ".", source = ".", ignore = true)
    List<PersonDTO> listPersonToDTOPerson(List<Person> personList);

    @Mapping(target = ".", source = ".", ignore = true)
    PersonDTOV2 persontoPersonDTOV2(Person person);

    @Mapping(target = ".", source = ".", ignore = true)
    Person personDTOV2toPerson(PersonDTOV2 personDTOV2);


}
