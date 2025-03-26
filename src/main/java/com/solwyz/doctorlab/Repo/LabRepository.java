package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.solwyz.doctorlab.Entity.Laboratory;

public interface LabRepository extends JpaRepository<Laboratory, Long>{

	Laboratory save(Laboratory lab);

	
	@Query("SELECT c FROM Laboratory c WHERE (:name IS NULL OR c.LabName LIKE %:name%) "
			+ "AND (:minRating IS NULL OR c.ratings >= :minRating) "
			+ "AND (:maxRating IS NULL OR c.ratings <= :maxRating)")
	List<Laboratory> findByFilters(@Param("name") String name, @Param("minRating") Double minRating,
			@Param("maxRating") Double maxRating);


}
