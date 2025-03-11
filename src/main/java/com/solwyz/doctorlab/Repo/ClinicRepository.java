package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	@Query("SELECT c FROM Clinic c WHERE (:name IS NULL OR c.clinicName LIKE %:name%) "
			+ "AND (:minRating IS NULL OR c.ratings >= :minRating) "
			+ "AND (:maxRating IS NULL OR c.ratings <= :maxRating)")
	List<Clinic> findByFilters(@Param("name") String name, @Param("minRating") Double minRating,
			@Param("maxRating") Double maxRating);

}
