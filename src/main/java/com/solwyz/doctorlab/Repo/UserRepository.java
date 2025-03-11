package com.solwyz.doctorlab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByMobile(String mobile);

}
