package com.js.SkillConnect_Crud.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	String name;
	String email;
	String bio;
	

	
	
	@OneToMany(mappedBy = "developer" ,cascade = CascadeType.ALL ,orphanRemoval = true)
	@JsonManagedReference
	private List<Skill> skills= new ArrayList<>();


	
	@OneToMany(mappedBy = "developer" ,cascade = CascadeType.ALL ,orphanRemoval = true)
	@JsonManagedReference
	private List<Project> projects= new ArrayList<>();;
	
	
	@OneToMany(mappedBy = "developer" ,cascade = CascadeType.ALL ,orphanRemoval = true)
	@JsonManagedReference
	private List<Experience> experiences= new ArrayList<>();;
	
//	@JsonProperty("fresher")
//	public boolean isfresher()
//	{
//		return experiences == null || experiences.isEmpty();
//	}
		
	
	
	
}
