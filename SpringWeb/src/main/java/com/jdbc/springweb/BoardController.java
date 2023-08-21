package com.jdbc.springweb;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.dao.BoardDAO2;
import com.jdbc.dto.BoardDTO;
import com.jdbc.util.MyUtil;

@Controller
public class BoardController {

	@Autowired
	@Qualifier("boardDAO2")
	BoardDAO2 dao;

	@Autowired
	MyUtil myUtil;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {

		return "index";
	}


	/*
	@RequestMapping(value = "/created.action", method = RequestMethod.GET)
	public String created() throws Exception{

		return "bbs/created";
	}
	 */


	@RequestMapping(value = "/created.action")
	public ModelAndView created() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/created");

		return mav;
	}


	@RequestMapping(value = "/created_ok.action", method = RequestMethod.POST)
	public String created_ok(BoardDTO dto, HttpServletRequest request) 
			throws Exception{

		int maxNum = dao.getMaxNum();

		dto.setNum(maxNum+1);
		dto.setIpAddr(request.getRemoteAddr());

		System.out.println(dto.getSubject());
		dao.insertData(dto);


		return "redirect:/list.action";
	}



	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest req) throws Exception{

		String cp = req.getContextPath();

		String pageNum = req.getParameter("pageNum");

		int currentPage = 1;

		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}

		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");

		if(searchValue==null) {
			searchKey = "subject";
			searchValue = "";
		}else {
			if(req.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}

		int dataCount = dao.getDataCount(searchKey, searchValue);

		int numPerPage = 5;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if(currentPage>totalPage) {
			currentPage = totalPage;
		}

		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;

		List<BoardDTO> lists = 
				dao.getLists(start, end, searchKey, searchValue);

		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");		
		}

		String listUrl = cp + "/list.action";

		if(!param.equals("")) {				
			listUrl += "?" + param;				
		}

		String pageIndexList =
				myUtil.pageIndexList(currentPage, totalPage, listUrl);

		String articleUrl = cp + "/article.action?pageNum=" + currentPage;

		if(!param.equals("")) {
			articleUrl += "&" + param;
		}

		//포워딩할 데이터
		req.setAttribute("lists", lists);
		req.setAttribute("pageIndexList", pageIndexList);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("articleUrl", articleUrl);




		return "bbs/list";

	}


	/*
	@RequestMapping(value = "/article.action", method = RequestMethod.GET)
	public String article(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	 */

	@RequestMapping(value = "/article.action", method = RequestMethod.GET)
	public ModelAndView article(HttpServletRequest req, HttpServletResponse resp) throws Exception{

		String cp = req.getContextPath();

		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");

		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");

		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}

		dao.updateHitCount(num);

		BoardDTO dto = dao.getReadData(num);

		if(dto==null) {
			//			String url = cp + "/list.action";
			//			resp.sendRedirect(url);

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action");

			return mav;
		}

		int lineSu = dto.getContent().split("\n").length;

		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));

		String param = "pageNum=" + pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");				
		}

		/*
		req.setAttribute("dto", dto);
		req.setAttribute("params", param);
		req.setAttribute("lineSu", lineSu);
		req.setAttribute("pageNum", pageNum);


		return "bbs/article";
		 */

		ModelAndView mav = new ModelAndView();

		mav.addObject("dto",dto);
		mav.addObject("params",param);
		mav.addObject("lineSu",lineSu);
		mav.addObject("pageNum",pageNum);

		mav.setViewName("bbs/article");

		return mav;


	}


	@RequestMapping(value = "/updated.action", method = RequestMethod.GET)
	public String update(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception{

		String cp = req.getContextPath();
	
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		BoardDTO dto = dao.getReadData(num);
		
		if(dto==null) {
			String url = cp + "/sbbs/list.action";
			resp.sendRedirect(url);
		}			
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");				
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("params", param);
		req.setAttribute("searchKey", searchKey);
		req.setAttribute("searchValue", searchValue);	
		
		return "bbs/updated";

	}
	
	
	@RequestMapping(value = "/updated_ok.action", method = RequestMethod.POST)
	public String updated_ok(BoardDTO dto,HttpServletRequest req, 
			HttpServletResponse resp) throws Exception{
		
		
		String pageNum = req.getParameter("pageNum");
		
		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}			
		
		
		dao.updateData(dto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");				
		}	
		
		
		return "redirect:/list.action?" + param;
		
	}
	
	

	@RequestMapping(value = "/deleted_ok.action", method = RequestMethod.GET)
	public String deleted_ok(HttpServletRequest req) throws Exception{
	
		int num =Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}	
		
		dao.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");				
		}

		return "redirect:/list.action?" + param;
}
	
}