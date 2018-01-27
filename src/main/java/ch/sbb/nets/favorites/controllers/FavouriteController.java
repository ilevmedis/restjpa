package ch.sbb.nets.favorites.controllers;

import ch.sbb.nets.favorites.data.model.Favourite;
import ch.sbb.nets.favorites.data.repository.FavouriteRepository;
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
public class FavouriteController {
  @Autowired
  FavouriteRepository favouriteRepository;
  GenericJpaController<Favourite> genericJpaController;

  public static final String MOUNT_PATH = "/favourite";
  public static final String MOUNTLABELS_PATH = "/favouritelabels";

  @PostConstruct
  private void initGenericOracleController() {
    genericJpaController = new GenericJpaController(favouriteRepository);
  }

  @RequestMapping(value = MOUNT_PATH, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Iterable<Favourite> getAllFavorites() {
    return genericJpaController.getAllRecords();
  }

  @RequestMapping(value = MOUNT_PATH + "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Favourite> getFavoriteById(@PathVariable("id") final Long id) {
    return genericJpaController.getRecordById(id);
  }

  @RequestMapping(value = MOUNT_PATH, method = RequestMethod.POST,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Favourite> createFavorite(@RequestBody final Favourite person) {
    return genericJpaController.createRecord(person);
  }

  @RequestMapping(value = MOUNT_PATH + "/{id}", method = RequestMethod.PUT,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Favourite> updateFavorite(@PathVariable("id") final Long id, @RequestBody final Favourite person) {
    return genericJpaController.updateRecord(id, person);
  }

  @RequestMapping(value = MOUNT_PATH + "/{id}", method = RequestMethod.DELETE,
          consumes = {MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Favourite> deleteFavorite(@PathVariable("id") final Long id) {
    return genericJpaController.deleteRecord(id);
  }

  @RequestMapping(value = MOUNTLABELS_PATH + "/{key}/{value}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Favourite>> getPersonByTag(@PathVariable("key") final String key, @PathVariable("value") final String value) {
    final List<Favourite> favourites = favouriteRepository.findByLabel(key, value);
    HttpStatus responseStatus = HttpStatus.OK;
    if (favourites == null || favourites.isEmpty()) {
      responseStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(favourites, responseStatus);
  }

}
