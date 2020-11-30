package ro.fasttrackit.curs17;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class PersonService {
    private List<Person> persons;

    public PersonService(Collection<Person> persons) {
        this.persons = new ArrayList<>();
        this.persons.addAll(persons);
    }


    public List<Person> getAllPersons() {
        return persons;
    }

    public List<String> getFullName() {
        List<String> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(person.getFirstName() + " " + person.getLastName());
        }

        return result;
    }

    public List<Person> getMajorPerosn() {
        return persons.stream()
                .filter(p -> p.getAge() > 17)
                .collect(toList());
    }

    public List<Person> getPersonOradea() {
        return persons.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("oradea"))
                .collect(toList());
    }

    public List<Person> getPersonOradeaCluj() {
        return persons.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("oradea")
                        || p.getCity().equalsIgnoreCase("cluj"))
                .collect(toList());
    }

    public List<String> firstNameCapitalized() {
        return persons.stream()
                .map(p -> p.getFirstName().toUpperCase())
                .collect(toList());
    }

    public List<Serializable> firstLetterLastName() {
        return persons.stream()
                .flatMap(p -> Stream.of(p.getFirstName(), p.getLastName().charAt(0) + ".", p.getAge(), p.getCity()))
                .collect(toList());
    }

    public List<Person> filterByAge() {
        return persons.stream()
                .filter(p -> p.getAge() > 18 && p.getAge() < 60)
                .collect(toList());
    }

    public List<Person> firstNameA() {
        return persons.stream()
                .filter(p->p.getFirstName().startsWith("A"))
                .collect(toList());
    }

    public List<Person> firstNameUniq() {
        return persons.stream()
                .collect(groupingBy(Person::getFirstName))
                .values().stream().map(p->(p.get(0)))
                .collect(toList());
    }

    public List<Person> sortByFirstName() {
        return persons.stream()
                .sorted(comparing(Person::getFirstName))
                .collect(toList());
    }

    public List<Person> sortByLastName() {
        return persons.stream()
                .sorted(comparing(Person::getLastName))
                .collect(toList());
    }

    public List<Person> sortByFLA() {
        return persons.stream()
                .sorted(comparing(Person::getFirstName).thenComparing(Person::getLastName).
                        thenComparing(Person::getAge))
                .collect(toList());
    }
}
