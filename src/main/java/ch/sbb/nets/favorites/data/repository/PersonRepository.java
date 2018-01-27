package ch.sbb.nets.favorites.data.repository;

import ch.sbb.nets.favorites.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	List<Person> findByLastName(@Param("name") String name);
	@Query("select p from Person p join fetch p.tags t where t.tag = :tagname")
	List<Person> findByTag(@Param("tagname") String tagname);
}
