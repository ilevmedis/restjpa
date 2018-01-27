package ch.sbb.nets.favorites.controllers;

import ch.sbb.nets.favorites.data.model.Person;
import ch.sbb.nets.favorites.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by ELIAS_LEVIS on 30/7/2017.
 */
@RestController
public class PersonController {
  @Autowired
  PersonRepository personRepository;
  GenericJpaController<Person> genericJpaController;

  @PostConstruct
  private void initGenericOracleController() {
    genericJpaController = new GenericJpaController(personRepository);
  }

  @RequestMapping(value = "/people", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Iterable<Person> getAllPersons() {
    return genericJpaController.getAllRecords();
  }

  @RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Person> getPersonById(@PathVariable("id") final Long id) {
    return genericJpaController.getRecordById(id);
  }

  @RequestMapping(value = "/people", method = RequestMethod.POST,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Person> createPerson(@RequestBody final Person person) {
    return genericJpaController.createRecord(person);
  }

  @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody final Person person) {
    return genericJpaController.updateRecord(id, person);
  }

  @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Person> deletePerson(@PathVariable("id") final Long id) {
    return genericJpaController.deleteRecord(id);
  }

  @RequestMapping(value = "/peoplewithtag/{tag}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Person>> getPersonByTag(@PathVariable("tag") final String tag) {
    final List<Person> persons = personRepository.findByTag(tag);
    HttpStatus responseStatus = HttpStatus.OK;
    if (persons == null || persons.isEmpty()) {
      responseStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(persons, responseStatus);
  }

}
