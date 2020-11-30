package ro.fasttrackit.curs17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceTest {

    PersonService service;
    List<Person> expected;

    @BeforeEach
    void setup() {
        expected = new ArrayList<>();
        expected = List.of(
                new Person("Szabo", "Levente", 60, "Oradea"),
                new Person("Szabo", "Marius", 16, "Turda"),
                new Person("Szabo", "Timea", 30, "Cluj"),
                new Person("Bontea", "Stefan", 56, "Targu Mures"),
                new Person("Apostol", "Paul", 62, "Suceava"),
                new Person("Bungau", "Marius", 15, "Cluj"),
                new Person("Apostol", "Claudiu", 17, "Arad"),
                new Person("Bungau", "George", 65, "Oradea")
        );
    }

    @Test
    @DisplayName("PersonService empty list")
    void emptyListTest() {

        PersonService personService = new PersonService(List.of());

        List<Person> recevied = personService.getAllPersons();

        assertThat(recevied).isEmpty();
    }

    @Test
    @DisplayName("PersonService add 8 persons")
    void addPersonsTest() {
        PersonService service = new PersonService(expected);
        List<Person> recevied = service.getAllPersons();

        assertThat(recevied).hasSize(8);
        assertThat(recevied).isEqualTo(expected);
    }

    @Test
    @DisplayName("list all the persons names: firstName lastName")
    void getFullNameTest() {

        PersonService service = new PersonService(expected);

        List<String> recevied = service.getFullName();

        assertThat(recevied).hasSize(8);
    }

    @Test
    @DisplayName("list all persons that  age >17  ")
    void personMajorTest() {

        PersonService service = new PersonService(expected);

        List<Person> recevied = service.getMajorPerosn();

        assertThat(recevied).hasSize(5);
    }

    @Test
    @DisplayName("list all persons from Oradea")
    void personOradeaTest() {

        PersonService service = new PersonService(expected);

        List<Person> recevied = service.getPersonOradea();

        assertThat(recevied).hasSize(2);
    }

    @Test
    @DisplayName("list all persons from Oradea Cluj")
    void personOradeaClujTest() {

        PersonService service = new PersonService(expected);

        List<Person> recevied = service.getPersonOradeaCluj();

        assertThat(recevied).hasSize(4);
    }

    @Test
    @DisplayName("list all firstNames CAPITALIZED")
    void firstNameCapitalizedTest() {

        PersonService service = new PersonService(expected);
        var firstNameCap = service.firstNameCapitalized().get(0);
        var firstName = service.getAllPersons().get(0).getFirstName();

        assertEquals(firstNameCap, firstName.toUpperCase());
    }

    @Test
    @DisplayName("- list all person names: firstName firstletter from last name: Stefan B.")
    void firstLetterLastNameTest() {

        PersonService service = new PersonService(expected);
        var lastNameChar = service.firstLetterLastName().get(1);

        assertEquals(lastNameChar, "C.");
    }

    @Test
    @DisplayName(" list all persons with 18 < age < 60 ")
    void perosonsWhitAgeTest() {

        PersonService service = new PersonService(expected);
        List<Person> received = service.filterByAge();
        assertThat(received).hasSize(2);
    }

    @Test
    @DisplayName(" list all persons having first name starting with A")
    void firstNameATest() {

        PersonService service = new PersonService(expected);
        List<Person> received = service.firstNameA();
        assertThat(received).hasSize(2);
    }

    @Test
    @DisplayName(" list all first names UNIQUELY")
    void firstNameUniqTest() {

        PersonService service = new PersonService(expected);
        List<Person> received = service.firstNameUniq();
        assertThat(received).hasSize(4);
    }

    @Test
    @DisplayName("sort the persons by first name")
    void sortByFirstNameTest() {

        PersonService service = new PersonService(expected);
        var received = service.sortByFirstName().get(0);
        assertEquals(received, new Person("Apostol", "Claudiu", 17, "Arad"));
    }

    @Test
    @DisplayName("sort the persons by last name")
    void sortByLastNameTest() {

        PersonService service = new PersonService(expected);
        var received = service.sortByLastName().get(0);
        assertEquals(received, new Person("Apostol", "Claudiu", 17, "Arad"));
    }

    @Test
    @DisplayName("sort the persons by first name, last name and then age")
    void sortByFLATest() {

        PersonService service = new PersonService(expected);
        var received = service.sortByFLA().get(0);
        assertEquals(received, new Person("Apostol", "Claudiu", 17, "Arad"));
    }
}
