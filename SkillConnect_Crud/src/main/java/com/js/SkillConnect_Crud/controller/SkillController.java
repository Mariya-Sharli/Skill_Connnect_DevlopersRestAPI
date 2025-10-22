package com.js.SkillConnect_Crud.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.js.SkillConnect_Crud.Entity.Skill;
import com.js.SkillConnect_Crud.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {
	@Autowired
	private SkillService service;
	

	@PostMapping("/developer/{developer_id}")
	public ResponseEntity<Map<String,Object>> createDeveloper(@PathVariable Long developer_id,@RequestBody Skill skill)
	{
		Skill dev = service.add(developer_id, skill);
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		map.put("status", "success");
		map.put("code", 201);
		map.put("message","data created successfully!");
		map.put("data", dev);
		return ResponseEntity.status(201).body(map);
		
	}
	
	@GetMapping("/developer/{developer_id}")
	public ResponseEntity<Map<String,Object>> find(@PathVariable Long  developer_id)
	{
		
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		List<Skill> dev = service.findSkill(developer_id);
		map.put("status", "success");
		map.put("code", 200);
		map.put("message","data fetched successfully!");
		map.put("data", dev);
		return ResponseEntity.status(200).body(map);
		
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Map<String,Object>> findByI(@PathVariable Long id)
//	{
//		Map<String,Object> map = new LinkedHashMap<String, Object>();
//		Optional<Skill> dev = service.findSkillid(id);
//		if(dev.isEmpty())
//		{
//			map.put("status", "success");
//			map.put("code", 200);
//			map.put("message","data fetched successfully!");
//			map.put("data", dev);
//			return ResponseEntity.status(200).body(map);
//		}
//		else
//			map.put("status", "error");
//		map.put("code", 204);
//		map.put("message","data not found !");
//		
//		return ResponseEntity.status(204).body(map);
//		
//		
//		
//	}
	
	
	@DeleteMapping("/developer/{developer_id}/skills/{id}")
	public ResponseEntity<Map<String,Object>> Delete(@PathVariable Long developer_id,@PathVariable Long id)
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
	
	
	@PutMapping("/developer/{developer_id}/skills/{id}")
	public ResponseEntity<Map<String,Object>> Update(@PathVariable Long developer_id,@RequestBody Skill skill,@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		Skill update = service.update(developer_id, skill, id);
		map.put("status", "success");
		map.put("code", 200);
		map.put("data",update);
		return ResponseEntity.ok(map);
	}
	
}

