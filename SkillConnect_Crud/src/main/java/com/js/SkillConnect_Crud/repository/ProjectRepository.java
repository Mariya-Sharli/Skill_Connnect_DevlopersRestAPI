package com.js.SkillConnect_Crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.SkillConnect_Crud.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	
	List<Project> findByDeveloperId(Long developer_id);

}
