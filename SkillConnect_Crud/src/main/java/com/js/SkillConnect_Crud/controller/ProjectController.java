package com.js.SkillConnect_Crud.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.SkillConnect_Crud.Entity.Project;
import com.js.SkillConnect_Crud.Entity.Skill;
import com.js.SkillConnect_Crud.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@PostMapping("/developer/{developer_id}")
	public ResponseEntity<Map<String,Object>> addProject(@PathVariable Long developer_id,@RequestBody Project project)
	{
		Project proj = service.add(developer_id, project);
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("Status", "success");
		map.put("code", 201);
		map.put("message", "project addded successsfully");
		map.put("Data", proj);
		return ResponseEntity.status(201).body(map);
	}
	
	@GetMapping("/developer/{developer_id}")
	public ResponseEntity<Map<String,Object>>  fetchProject(@PathVariable Long developer_id)
	{
		List<Project> proj = service.find(developer_id);
		Map<String,Object> map = new LinkedHashMap<String, Object>()
;
		map.put("status", "sucsess");
		map.put("code", 200);
		map.put("message", "project fetched successfullt");
		map.put("data", proj);
		return ResponseEntity.status(200).body(map);
	}
	
	@DeleteMapping("/developer/{developer_id}/project/{id}")
	public ResponseEntity<Map<String,Object>> deleteProj(@PathVariable Long developer_id,@PathVariable Long id )
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		boolean del = service.delete(developer_id, id);
		if(del)
		{
			map.put("status", "success");
			map.put("code", 200);
			map.put("message", "Data deleted Successfully");
			return ResponseEntity.status(200).body(map);
		}
		else
		{
			map.put("status", "error");
			map.put("code", 404);
			map.put("message", "not found");
			return ResponseEntity.status(404).body(map);
		}
	}
	
	@PutMapping("/developer/{developer_id}/project/{id}")
	public ResponseEntity<Map<String,Object>> updateProj(@PathVariable Long developer_id,@RequestBody Project project,@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		Project update = service.update(developer_id, project, id);
		map.put("status", "success");
		map.put("code", 200);
		map.put("data",update);
		return ResponseEntity.ok(map);
	}
}
