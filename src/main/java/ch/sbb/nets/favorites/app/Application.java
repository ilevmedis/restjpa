
package ch.sbb.nets.favorites.app;

import ch.sbb.nets.favorites.controllers.PersonController;
import ch.sbb.nets.favorites.data.model.Person;
import ch.sbb.nets.favorites.data.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PersonController.class})
@EntityScan(basePackageClasses=Person.class)
@EnableJpaRepositories(basePackageClasses = {PersonRepository.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
