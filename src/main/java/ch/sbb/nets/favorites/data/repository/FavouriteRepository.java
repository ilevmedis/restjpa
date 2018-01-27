package ch.sbb.nets.favorites.data.repository;

import ch.sbb.nets.favorites.data.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

	List<Favourite> findByUserid(@Param("userid") String userid);
	List<Favourite> findByUseridAndProfile(@Param("userid") String userid, @Param("profile") String profile);
	@Query("select f from Favourite f join fetch f.labels l where l.key = :key and l.value = :value")
	List<Favourite> findByLabel(@Param("key") String key, @Param("value") String value);
}
