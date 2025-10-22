package com.js.SkillConnect_Crud.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.js.SkillConnect_Crud.Entity.Developer;
import com.js.SkillConnect_Crud.service.DeveloperService;


@RestController
@RequestMapping("/developer")
public class DeveloperController {
	
	@Autowired
	private DeveloperService service;
	
	@PostMapping
	public ResponseEntity<Map<String,Object>> createDeveloper(@RequestBody Developer developer)
	{
		Developer dev = service.add(developer);
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		map.put("status", "success");
		map.put("code", 201);
		map.put("message","data created successfully!");
		map.put("data", dev);
		return ResponseEntity.status(201).body(map);
		
	}
	
	@GetMapping
	public ResponseEntity<Map<String,Object>> find(
			@RequestParam (defaultValue = "id") String sort,
			@RequestParam (defaultValue = "false") boolean  desc,
			@RequestParam (defaultValue = "1") int page,
			@RequestParam (defaultValue = "3") int size,
			@RequestParam (required =false ) String skills
			)
	{
		
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		List<Developer> dev = service.findDeveloper(sort, desc, page, size, skills);
		map.put("status", "success");
		map.put("code", 200);
		map.put("message","data fetched successfully!");
		map.put("data", dev);
		return ResponseEntity.status(200).body(map);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> findByI(@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		Optional<Developer> dev = service.FindDeveloperbyId(id);
		if(dev.isPresent())
		{
			map.put("status", "success");
			map.put("code", 200);
			map.put("message","data fetched successfully!");
			map.put("data", dev);
			return ResponseEntity.status(200).body(map);
		}
		else
			map.put("status", "error");
		map.put("code", 404);
		map.put("message","data not found !");
		
		return ResponseEntity.status(404).body(map);
		
		
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Object>> Delete(@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		boolean del = service.delete(id);
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
			map.put("message", "Data not found");
			return ResponseEntity.status(404).body(map);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String,Object>> Update(@RequestBody Developer developer,@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		Developer update = service.update(developer,id);
		map.put("status", "success");
		map.put("code", 200);
		map.put("data",update);
		return ResponseEntity.ok(map);
	}
	
}
