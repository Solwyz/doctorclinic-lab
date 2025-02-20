package com.medo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medo.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User save(User newUser);

}
