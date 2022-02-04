package com.example.testbatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
	
	//employee(company_id, employee_name, employee_address) 는 DB column 값이다.
	@Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.company_id}, #{employee.name}, #{employee.address})")
	@Options(useGeneratedKeys=true, keyProperty="id")	//생성된 키를 id 라는 property에 설정
	int insert(@Param("employee") Employee employee);
	
	@Select("SELECT * FROM employee")
	@Results(id="EmployeeMap", value= {
			@Result(property="name", column="employee_name"),
			@Result(property="address", column="employee_address")
	})
	List<Employee> getAll();
	
	@Select("SELECT * FROM employee WHERE id=#{id}")
	@ResultMap("EmployeeMap")
	Employee getById(@Param("id") int id);
	
	@Select("SELECT * FROM employee WHERE company_id=#{companyId}")
	@ResultMap("EmployeeMap")
	List<Employee> getByCompanyId(@Param("companyId") int company_id);

}
