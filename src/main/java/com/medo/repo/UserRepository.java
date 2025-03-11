package com.medo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByMobile(String mobile);
    boolean existsByMobile(String mobile);
}
