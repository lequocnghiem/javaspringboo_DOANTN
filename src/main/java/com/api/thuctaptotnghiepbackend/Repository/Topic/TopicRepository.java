package com.api.thuctaptotnghiepbackend.Repository.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAll(Pageable pageable);
}
