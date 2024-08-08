package br.com.devthiagoramon.restwithspringbootandjavaerutio.repository;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
