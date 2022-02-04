package com.example.testbatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	List<Company> getAll() {
		List<Company> companyList = companyMapper.getAll();
		if(companyList != null && companyList.size() > 0) {
			for(Company company : companyList) {
				company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
			}
		}		
		return companyList;
	}
}
