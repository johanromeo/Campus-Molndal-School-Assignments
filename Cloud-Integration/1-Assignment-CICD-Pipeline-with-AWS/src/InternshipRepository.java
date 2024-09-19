package com.jromeo.internshipbackend.repository;

import com.jromeo.internshipbackend.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Integer> {
}
