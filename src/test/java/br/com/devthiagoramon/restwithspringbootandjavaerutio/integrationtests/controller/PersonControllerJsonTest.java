package br.com.devthiagoramon.restwithspringbootandjavaerutio.integrationtests.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.configs.TestConfigs;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.integrationtests.dto.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest implements WithAssertions {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDTO personDTO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        personDTO = new PersonDTO();
    }

    @Test
    @Order(1)
    public void testCreate() throws IOException {
        mockPerson();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "https://erudio.com.br")
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                                 .spec(specification)
                                 .contentType(TestConfigs.HEADER_PARAM_CONTENT_TYPE_JSON)
                                 .body(personDTO)
                                 .when()
                                 .post()
                                 .then().statusCode(200)
                                 .extract().body().asString();
        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);

        personDTO = createdPerson;
        assertThat(personDTO.getId()).isNotNull();
        assertThat(personDTO.getAddress()).isNotNull();
        assertThat(personDTO.getGender()).isNotNull();
        assertThat(personDTO.getLastName()).isNotNull();
        assertThat(personDTO.getFirstName()).isNotNull();
        assertThat(personDTO.getId()).isGreaterThan(0);

        assertThat(personDTO.getFirstName()).isEqualTo("Richard");
        assertThat(personDTO.getLastName()).isEqualTo("Stallman");
        assertThat(personDTO.getAddress()).isEqualTo("New York City");
        assertThat(personDTO.getGender()).isEqualTo("M");

    }

    @Test
    public void testList() throws IOException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:3000")
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                .spec(specification)
                .contentType(TestConfigs.HEADER_PARAM_CONTENT_TYPE_YML)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        var persons = objectMapper.readValue(content, ArrayList.class);

        assertThat(persons.size()).isEqualTo(5);
    }



    private void mockPerson() {
        personDTO.setFirstName("Richard");
        personDTO.setLastName("Stallman");
        personDTO.setAddress("New York City");
        personDTO.setGender("M");
    }
}
