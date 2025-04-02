package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor> findByClinicId(Long clinicId);

	List<Doctor> findByDepartment(String department);

	@Query("SELECT d FROM Doctor d WHERE (:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')))"
			+ " AND (:minRating IS NULL OR d.ratings >= :minRating)"
			+ " AND (:maxRating IS NULL OR d.ratings <= :maxRating)"
			+ " AND (:availabilityTimes IS NULL OR :availabilityTimes MEMBER OF d.availabilityTimes)")
	List<Doctor> findByFilters(@Param("name") String name, @Param("minRating") Double minRating,
			@Param("maxRating") Double maxRating, @Param("availabilityTimes") String availabilityTimes);

	@Query("SELECT d FROM Doctor d WHERE d.department.departmentName = :departmentName")
	List<Doctor> findByDepartmentName(@Param("departmentName") String departmentName);

	

}
