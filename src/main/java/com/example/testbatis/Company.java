package com.example.testbatis;

import java.util.List;

import lombok.Data;

@Data
public class Company {
	private int id;
	private String name;
	private String address;
	private List<Employee> employeeList;
}

/*
//Spring (XML 방식)
@Data
public class CompanyVO {
	// 3개의 프로퍼티를 갖는 클래스 생성
	private int id;
	private String name;
	private String address;
}*/
