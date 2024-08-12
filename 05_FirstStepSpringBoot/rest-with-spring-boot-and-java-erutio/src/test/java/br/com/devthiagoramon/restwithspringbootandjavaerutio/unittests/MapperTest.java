package br.com.devthiagoramon.restwithspringbootandjavaerutio.unittests;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.unittests.mapper.PersonMapperTest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class MapperTest implements WithAssertions {


    private Person mockPerson() {
        return new Person(123L, "Thiago", "Barros", "Rua Barroncas", "M");
    }

    @Test
    public void convertToDTO() {
        Person person = mockPerson();
        PersonDTO personDTO = PersonMapperTest.INSTANCE.personToPersonDTO(person);
        assertThat(personDTO.getId()).isEqualTo(person.getId());
        assertThat(personDTO.getFirstName()).isEqualTo(person.getFirstName());
    }


    @Test
    public void dtoToPerson() {
        Person person = mockPerson();
        PersonDTO personDTO = PersonMapperTest.INSTANCE.personToPersonDTO(person);
        Person person2 = PersonMapperTest.INSTANCE.personDTOTOPerson(personDTO);
        assertThat(person2.getId()).isEqualTo(personDTO.getId());
        assertThat(person2.getFirstName()).isEqualTo(personDTO.getFirstName());
    }

    @Test
    public void listPersonToListPersonDTO() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(mockPerson());
        personArrayList.add(mockPerson());
        personArrayList.add(mockPerson());
        personArrayList.add(mockPerson());
        personArrayList.add(mockPerson());
        personArrayList.add(mockPerson());


        // Get the second element and convert to DTO
        Person p2 = personArrayList.get(1);
        PersonDTO person2DTO = PersonMapperTest.INSTANCE.personToPersonDTO(p2);
        assertThat(person2DTO.getId()).isEqualTo(p2.getId());

        // Transform to list dto
        ArrayList<PersonDTO> personDTOArrayList = (ArrayList<PersonDTO>) PersonMapperTest.INSTANCE.listPersonToDTOPerson(personArrayList);


        // Get the second element from array dto and convert to DTO
        PersonDTO p2DTO = personDTOArrayList.get(1);
        Person person2 = PersonMapperTest.INSTANCE.personDTOTOPerson(p2DTO);
        assertThat(person2.getId()).isEqualTo(p2DTO.getId());

        System.out.println("Person");
        for (Person person:
            personArrayList){
            System.out.println(person.toString());
        }
        System.out.println("Person DTO");
        for (PersonDTO personDTO:
                personDTOArrayList){
            System.out.println(personDTO.toString());
        }

    }

}
