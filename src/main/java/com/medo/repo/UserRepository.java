package com.medo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User save(User newUser);

	Optional<User> findByMobile(String mobileNumber);

}
