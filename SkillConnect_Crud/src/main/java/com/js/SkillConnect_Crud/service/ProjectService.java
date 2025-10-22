package com.js.SkillConnect_Crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.SkillConnect_Crud.Entity.Developer;
import com.js.SkillConnect_Crud.Entity.Project;

import com.js.SkillConnect_Crud.exception.dataNotFoundException;
import com.js.SkillConnect_Crud.repository.DeveloperRepository;
import com.js.SkillConnect_Crud.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repo;
	
	@Autowired
	private DeveloperRepository dev_repo;
	
	
	
	public Project add(Long developer_id ,Project project)
	{
		Developer developer = dev_repo.findById(developer_id).orElseThrow(()-> new dataNotFoundException("Developer not found"));
		project.setDeveloper(developer);
		return repo.save(project);
	}
	
	
	public List<Project> find(long developer_id)
	{
		Developer developer = dev_repo.findById(developer_id).orElseThrow(()-> new dataNotFoundException("Developer not found"));
		return repo.findByDeveloperId(developer_id);
	}
	
	public  boolean delete(Long developer_id,Long id)
	{
		
		Project s = repo.findById(id).orElseThrow(()->new dataNotFoundException("Data not found"));
		if(!s.getDeveloper().getId().equals(developer_id))
		{
			throw new dataNotFoundException("Project not belong to that developer");
		}
		
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Project update(Long developer_id,Project project ,Long id)
	{
		Project s = repo.findById(id).orElseThrow(()->new dataNotFoundException("Data not found"));
		if(!s.getDeveloper().getId().equals(developer_id))
			throw new dataNotFoundException("Project not belong to that developer");
		if(project.getTitle() !=null)
			s.setTitle(project.getTitle());
		if(project.getDescription() !=null)
			s.setDescription(project.getDescription());
		if(project.getGithubLink() !=null)
			s.setGithubLink(project.getGithubLink());
		return repo.save(s);
	}
	
	
	

}
