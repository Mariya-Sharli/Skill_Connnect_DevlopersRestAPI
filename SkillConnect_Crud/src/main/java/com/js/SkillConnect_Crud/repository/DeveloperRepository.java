package com.js.SkillConnect_Crud.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.SkillConnect_Crud.Entity.Developer;

public interface DeveloperRepository extends JpaRepository<Developer,Long>{
	@Query("SELECT DISTINCT d FROM Developer d JOIN d.skills s WHERE s.name LIKE %:skill%")
		List<Developer> findBySkill(@Param("skills") String skills,PageRequest pagenation);
}
