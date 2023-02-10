package com.macgrouptech.macgrouptechblogapplication.repositories;

import com.macgrouptech.macgrouptechblogapplication.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
