package com.example.expensetracker.repository;

import com.example.expensetracker.model.entity.RoleName;
import com.example.expensetracker.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Roles> findByRoleName(RoleName roleName);

}