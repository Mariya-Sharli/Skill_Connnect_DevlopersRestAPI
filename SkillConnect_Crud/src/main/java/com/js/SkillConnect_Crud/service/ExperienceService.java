package com.js.SkillConnect_Crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.SkillConnect_Crud.Entity.Developer;
import com.js.SkillConnect_Crud.Entity.Experience;
import com.js.SkillConnect_Crud.exception.dataNotFoundException;
import com.js.SkillConnect_Crud.repository.DeveloperRepository;
import com.js.SkillConnect_Crud.repository.ExperienceRepository;

@Service 

public class ExperienceService {
	
	
	@Autowired
	private ExperienceRepository repo;
	
	@Autowired
	private DeveloperRepository devrepo;
	
	
	public Experience add(Experience exp,Long developer_id)
	{
		Developer developer = devrepo.findById(developer_id).orElseThrow(()->new dataNotFoundException("Developer not found"));
		exp.setDeveloper(developer);
		return repo.save(exp);
	}
	
	public List<Experience> fetchExp(Long developer_id)
	{
		Developer developer = devrepo.findById(developer_id).orElseThrow(()->new dataNotFoundException("Developer not found"));
		return repo.findByDeveloperId(developer_id);
	}
	
	public boolean delete(Long developer_id,Long id)
	{
		Experience ex = repo.findById(id).orElseThrow(()->new dataNotFoundException("Developer not found"));
		if(!ex.getDeveloper().getId().equals(developer_id))
			throw new dataNotFoundException("experience not belong to that developer");
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	
	}
	
	public Experience  update(Long developer_id,Experience exp,Long id)
	{
		Experience ex = repo.findById(id).orElseThrow(()->new dataNotFoundException("Developer not found"));
		if(!ex.getDeveloper().getId().equals(developer_id))
			throw new dataNotFoundException("experience does not belong to developer");
		if(exp.getCompany_name() !=null)
			ex.setCompany_name(exp.getCompany_name());
		if(exp.getPosition()!=null)
			ex.setPosition(exp.getPosition());
		if(exp.getYearsofexperince()!=0)
			ex.setYearsofexperince(exp.getYearsofexperince());
		if(exp.getDescription()!=null)
			ex.setDescription(exp.getDescription());
			return repo.save(ex);
			
	}
	

}
