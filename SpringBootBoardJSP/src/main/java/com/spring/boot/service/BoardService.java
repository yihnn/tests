package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.BoardDTO;


//Mapper를 만들어 놓고 service(I)에 메소드를 똑같이 만들면
//자동으로 연결됨
public interface BoardService {

	
	public int maxNum() throws Exception;
	
	public void insertData(BoardDTO dto) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<BoardDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public BoardDTO getReadData(int num) throws Exception;
	
	public void updateHitCount(int num) throws Exception;
	
	public void updateData(BoardDTO dto) throws Exception;
	
	public void deleteData(int num) throws Exception;
	
	
}
