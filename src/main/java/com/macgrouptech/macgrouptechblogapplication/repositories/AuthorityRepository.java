package com.macgrouptech.macgrouptechblogapplication.repositories;

import com.macgrouptech.macgrouptechblogapplication.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
