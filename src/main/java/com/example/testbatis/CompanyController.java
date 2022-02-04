package com.example.testbatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired	//생성자 주입
	private CompanyMapper companyMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("")
	public Company post(@RequestBody Company company) {
		companyMapper.insert(company);
		return company;
	}
	
	@GetMapping("")
	public List<Company> getAll() {
		return companyMapper.getAll();
	}
	
	@GetMapping("/{id}")
	public Company getById(@PathVariable("id") int id) {
		return companyMapper.getById(id);
	}

}
