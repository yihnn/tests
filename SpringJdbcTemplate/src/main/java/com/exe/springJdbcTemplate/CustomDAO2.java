package com.exe.springJdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CustomDAO2 {


	//spring의 DAO
	
	//의존성 주입 받기
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	public void insertData(CustomDTO dto) {

		StringBuilder sql = new StringBuilder();

//		sql.append("insert into custom (id,name,age) values (?,?,?)");
//
//		jdbcTemplate.update(sql.toString(), 
//				dto.getId(),dto.getName(),dto.getAge());
		
		//NamedParameterJdbcTemplate
		sql.append("insert into custom (id,name,age) values (:id,:name,:age)");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("id", dto.getId());
		params.addValue("name", dto.getName());
		params.addValue("age", dto.getAge());
		
		namedJdbcTemplate.update(sql.toString(), params);

	}


	public void updateData(CustomDTO dto) {

		StringBuilder sql = new StringBuilder();

		sql.append("update custom set name=?, age=? where id=?");

		jdbcTemplate.update(sql.toString(), 
				dto.getName(), dto.getAge(), dto.getId());
	}



	public void deleteDate(int id) {

		StringBuilder sql = new StringBuilder();

		sql.append("delete custom where id=?");

		jdbcTemplate.update(sql.toString(),id);

	}



	public List<CustomDTO> getList(){


		StringBuilder sql = new StringBuilder();

		sql.append("select id,name,age from custom");


		List<CustomDTO> lists = 
				jdbcTemplate.query(sql.toString(), 
						new RowMapper<CustomDTO>() {	//무명의 클래스(반복문)
							//RowMapper: 인터페이스
							public CustomDTO mapRow(ResultSet rs, int rowNum) 
									throws SQLException {

						CustomDTO dto = new CustomDTO();

						dto.setId(rs.getInt("id"));
						dto.setName(rs.getString("name"));
						dto.setAge(rs.getInt("age"));


						return dto;
						
						//dto-> <CustomDTO> -> lists 를 통해 데이터가 나감
					}

				});

		return lists;

	}

	public CustomDTO getReadData(int id){

		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,name,age from custom where id=?");

		CustomDTO dtoOne = jdbcTemplate.queryForObject(sql.toString(), 
				new RowMapper<CustomDTO>() {

					public CustomDTO mapRow(ResultSet rs, int rowNum) 
							throws SQLException {
						
						CustomDTO dto = new CustomDTO();

						dto.setId(rs.getInt("id"));
						dto.setName(rs.getString("name"));
						dto.setAge(rs.getInt("age"));

						return dto;
						
					}
			
		},id);
		
				return dtoOne;
	}








}
