package com.js.SkillConnect_Crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.SkillConnect_Crud.Entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
		
	List<Experience> findByDeveloperId(Long Developer_id);
}
