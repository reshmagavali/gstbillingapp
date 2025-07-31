package com.fullstack.repository;

import com.fullstack.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Admin findByEmail(String email);

    public Admin findByName(String name);

}
