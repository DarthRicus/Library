package kz.orynbay.springcourse.dao;

import kz.orynbay.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Getting all my persons from database
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new BeanPropertyRowMapper<>(Person.class),
                        new Object[]{id})
                .stream().findAny().orElse(null);
    }
    public Person show(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName = ?", new BeanPropertyRowMapper<>(Person.class),
                        new Object[]{fullName})
                .stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO  Person(fullName, yob) VALUES(?,?)", person.getFullName(), person.getYob());
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullName = ?, yob =?  WHERE person_id = ?", updatedPerson.getFullName(),
                updatedPerson.getYob(), id) ;
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
    }

}
