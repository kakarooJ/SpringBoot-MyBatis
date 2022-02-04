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
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	/*	Post(RequestBody) method 의 인자는 Employee 이다.
	 *  Database에 들어갈 JSON 형태의 data값은 DB column 값이 아니라 Employee class의 내부 객체멤버로 할당해야 한다.
	 * 즉, 아래 name이 DB column 이름인 employee_name으로 하면 안 되고, class 멤버 변수인 name으로 지정되어야 한다. 
	 * 헷갈리는 부분이니 유의하자
 	{
	    "company_id": 13,
	    "name": "홍길남",
	    "address": "서울시 강서구 강서3동"
	}
	 */
	@PostMapping("")
	public Employee post(@RequestBody Employee employee) {
		employeeMapper.insert(employee);
		return employee;	
	}
	
	@GetMapping("")
	public List<Employee> getAll() {
		return employeeMapper.getAll();
	}
	
	@GetMapping("/{id}")
	public Employee getById(@PathVariable("id") int id) {
		return employeeMapper.getById(id);
	}
	
	@GetMapping("/{id}/employee")
	public List<Employee> getByCompanyId(@PathVariable("id") int id) {
		return employeeMapper.getByCompanyId(id);
	}
	

}
