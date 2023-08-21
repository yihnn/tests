package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.BoardDTO;
import com.spring.boot.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	//("/*")로 주소가 Controller로 들어오면
	//BoardController -> BoardService(I) -> BoardServiceImple(C) ->
	//BoardMapper(I) -> boardMapper.xml
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public int maxNum() throws Exception {
		
		return boardMapper.maxNum();
	}

	@Override
	public void insertData(BoardDTO dto) throws Exception {
		
		boardMapper.insertData(dto);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return boardMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<BoardDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		
		return boardMapper.getLists(start, end, searchKey, searchValue);
	}

	@Override
	public BoardDTO getReadData(int num) throws Exception {
		
		return boardMapper.getReadData(num);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		boardMapper.updateHitCount(num);
	}

	@Override
	public void updateData(BoardDTO dto) throws Exception {
		boardMapper.updateData(dto);
		
	}

	@Override
	public void deleteData(int num) throws Exception {
		boardMapper.deleteData(num);
		
	}
	
	
}
