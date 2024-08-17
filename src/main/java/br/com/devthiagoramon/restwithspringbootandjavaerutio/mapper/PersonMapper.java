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

    @Mapping(target = "key", source = "id")
    PersonDTO personToPersonDTO(Person person);

    @Mapping(target = "id", source = "key")
    Person personDTOTOPerson(PersonDTO personDTO);

    @Mapping(target = "key", source = "id")
    List<PersonDTO> listPersonToDTOPerson(List<Person> personList);

    @Mapping(target = ".", source = ".")
    PersonDTOV2 persontoPersonDTOV2(Person person);

    @Mapping(target = ".", source = ".")
    Person personDTOV2toPerson(PersonDTOV2 personDTOV2);


}
