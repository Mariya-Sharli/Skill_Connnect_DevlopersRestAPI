package com.js.SkillConnect_Crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.SkillConnect_Crud.Entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	// In SkillRepository-->find all the skill taht belong to to eacgh devoper id 
	List<Skill> findByDeveloperId(Long developerId);


}
