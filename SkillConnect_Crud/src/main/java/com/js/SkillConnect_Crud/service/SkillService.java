package com.js.SkillConnect_Crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.SkillConnect_Crud.Entity.Developer;
import com.js.SkillConnect_Crud.Entity.Skill;
import com.js.SkillConnect_Crud.exception.dataNotFoundException;
import com.js.SkillConnect_Crud.repository.DeveloperRepository;
import com.js.SkillConnect_Crud.repository.SkillRepository;

@Service
public class SkillService {
	
	@Autowired
	private SkillRepository repo;
	
	 @Autowired
	    private DeveloperRepository developerRepository;
	
	
	public Skill add(Long developer_id ,Skill skill)
	{
	     Developer developer = developerRepository.findById(developer_id)
	                .orElseThrow(() -> new dataNotFoundException("Developer not found with id: " + developer_id));
	        skill.setDeveloper(developer);
		return repo.save(skill);
	}
		
	
	
	public List<Skill> findSkill(Long developer_id)
	
	{
		Developer developer = developerRepository.findById(developer_id)
                .orElseThrow(() -> new dataNotFoundException("Developer not found with id: " + developer_id));
//        skill.setDeveloper(developer);

		return repo.findByDeveloperId(developer_id);
	}
	
	
//	public Optional<Skill> findSkillid(Long id)
//	
//	{
//
//		return repo.findById(id);
//	}
//	
	public boolean delete(Long developer_id,Long id)
	{
		Skill s= repo.findById(id).orElseThrow(()-> new dataNotFoundException("Data not found"));
		if (!s.getDeveloper().getId().equals(developer_id)) {
	        throw new dataNotFoundException("Skill does not belong to this developer");
	    }
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	public Skill update(Long developer_id,Skill skill,Long id)
	{
		Skill s= repo.findById(id).orElseThrow(()-> new dataNotFoundException("Data not found"));
		 if (!s.getDeveloper().getId().equals(developer_id)) {
		        throw new dataNotFoundException("Skill does not belong to this developer");
		    }
		if(skill.getName() !=null)
			s.setName(skill.getName());
		if(skill.getLevel() !=null)
			s.setLevel(skill.getLevel());
		return repo.save(s);

	}
	
}
