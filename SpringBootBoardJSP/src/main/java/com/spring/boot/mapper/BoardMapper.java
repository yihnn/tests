package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.BoardDTO;

//sql에 엑세스할 수 있는 인터페이스 생성

@Mapper
public interface BoardMapper {

	public int maxNum() throws Exception;
	
	public void insertData(BoardDTO dto) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<BoardDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public BoardDTO getReadData(int num) throws Exception;
	
	public void updateHitCount(int num) throws Exception;
	
	public void updateData(BoardDTO dto) throws Exception;
	
	public void deleteData(int num) throws Exception;
	
	
	
}
