package br.com.devthiagoramon.restwithspringbootandjavaerutio.mapper;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonMapperCustomImpl {

    public static List<PersonDTO> toListPersonDTO(List<Person> personList){
        ArrayList<PersonDTO> personDTOS = new ArrayList<>();
        personList.forEach(person -> {
            personDTOS.add(PersonMapper.INSTANCE.personToPersonDTO(person));
        });
        return personDTOS;
    }

}
