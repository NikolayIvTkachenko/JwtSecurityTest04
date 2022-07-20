package com.rsh.jwt_demo_auth_04.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsh.jwt_demo_auth_04.model.ERole;
import com.rsh.jwt_demo_auth_04.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {	
	
	Optional<Role> findByName(ERole name);
	

}
