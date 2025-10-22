package com.js.SkillConnect_Crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.js.SkillConnect_Crud.Entity.Developer;
import com.js.SkillConnect_Crud.exception.dataNotFoundException;
import com.js.SkillConnect_Crud.repository.DeveloperRepository;

@Service
public class DeveloperService {
	
	@Autowired
	private DeveloperRepository repo;
	
	//create
	public Developer add(Developer developer)
	{
		// Ensure bidirectional linking
	    if (developer.getSkills() != null) {
	        developer.getSkills().forEach(skill -> skill.setDeveloper(developer));
	    }

	    if (developer.getProjects() != null) {
	        developer.getProjects().forEach(project -> project.setDeveloper(developer));
	    }
	    if(developer.getExperiences() !=null)
	    	developer.getExperiences().forEach(experience ->experience.setDeveloper(developer));

		return repo.save(developer);
	}
	
	
	//fetch--pagenation--filter--sort
	public List<Developer> findDeveloper(String sort,boolean desc,int page,int size,String skill)
	{
		Sort sorting = desc?Sort.by(sort).descending():Sort.by(sort).ascending();
		
		PageRequest pagenation  = PageRequest.of(page-1, size,sorting);
		
		List<Developer> dev = new ArrayList<Developer>();
		
		if(skill ==null)
		{
			dev = repo.findAll(pagenation).getContent();
		}
		else
			dev = repo.findBySkill(skill, pagenation);
		
		
//		
//		if (isfresher != null) {
//	        dev = dev.stream()
//	                .filter(d -> d.isfresher() == isfresher)
//	                .toList();
//	    }
		
		if(dev.isEmpty())
		{
			throw new dataNotFoundException(skill);
		}
		else
		return dev;
	}
	
	
	

	//fetchbyId
	public Optional<Developer> FindDeveloperbyId(Long id){
		return repo.findById(id);
		
	}
	
	//delete
	public boolean delete(Long id)
	{
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	//update
	public Developer update(Developer developer,Long id)
	{
		Developer olddev = repo.findById(id).orElseThrow(()-> new dataNotFoundException("Data not found"));
		if(developer.getName() !=null)
			olddev.setName(developer.getName());
		if(developer.getEmail()!= null)
			olddev.setEmail(developer.getEmail());
		if(developer.getBio() !=null)
			olddev.setBio(developer.getBio());
		return repo.save(olddev);
	}

}
