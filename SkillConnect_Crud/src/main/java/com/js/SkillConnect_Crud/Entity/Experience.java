package com.js.SkillConnect_Crud.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String Company_name;
	private String position;
	private int yearsofexperince;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "developer_id")
	@JsonBackReference
	private Developer developer;

}
