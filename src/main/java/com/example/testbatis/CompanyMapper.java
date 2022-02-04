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
import org.apache.ibatis.annotations.Many;

@Mapper
public interface CompanyMapper {

	@Insert("INSERT INTO company(name, address) VALUES(#{company.name}, #{company.address})")
	@Options(useGeneratedKeys=true, keyProperty="id")	//생성된 키를 id 라는 property에 설정
	int insert(@Param("company") Company company);
	
	@Select("SELECT * FROM company")
	/*
	 * @Results({
	 * 
	 * @Result(property="name", column="name"),
	 * 
	 * @Result(property="address", column="address") })
	 */
	@Results(id="CompanyMap", value={
		@Result(property="name", column="name"),
		@Result(property="address", column="address"),
		//여러개의 sub query를 수행, EmployeeMapper.getByCompanyId 의 id라는 칼럼을 파라미터로 사용
		@Result(property="employeeList", column="id", many=@Many(select="com.example.testbatis.EmployeeMapper.getByCompanyId"))
	})
	List<Company> getAll();
	
	@Select("SELECT * FROM company WHERE id=#{id}")
	@ResultMap("CompanyMap")	//중복되는 query를 ResultMap으로 등록해서 사용
	Company getById(@Param("id") int id);
	
	/*
	// Spring (XML)
  	<resultMap type="com.example.testbatis.CompanyVO" id="companyMap">
		<id property="id" column="id" />
		<result property="name" column="name" />
		<result property="address" column="address" />
	</resultMap>

	<select id="getList" resultMap="companyMap" resultType="com.example.testbatis.CompanyVO">
		select * from company 
	</select>
	 */
}
