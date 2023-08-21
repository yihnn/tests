package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.BoardDTO;
import com.spring.boot.service.BoardService;
import com.spring.boot.util.MyUtil;

@RestController
public class BoardController {

	//의존성주입(생성자 주입, 생성자를 통한 의존성 주입)
	@Resource
	private BoardService boardService;
	
	//의존성 주입(필드 주입, 클래스의 필드에 직접 객체를 주입)
	//@Autowired를 붙여 스프링 IoC 컨테이너에서 자동으로 주입받아 사용
	@Autowired
	MyUtil myUtil;
	
	
	/*public ModelAndView 사용자정의( throws Exception*/
	//ModelAndView 객체 생성(ModelAndView 변수명 = new 생성자();
	//mav.setViewName(html(띄울 페이지))
	//return mav
	//**Get방식: return값 띄울 페이지, post방식: return값 redirect할 주소
	
	
	//"/"로 들어오는 모든 요청을 Get방식으로 처리
	//@RequestMapping(value = "/")
	@GetMapping("/")
	public ModelAndView index() throws Exception{
		
		//객체 생성
		ModelAndView mav = new ModelAndView();
		
		//"/"로 들어오는 요청은 index.html화면을 띄움
		mav.setViewName("index");
		
		return mav;
	}
	
	
	//"created.action"으로 들어오는 주소는 Get방식으로 처리
	//@RequestMapping(value = "/created.action", method = RequestMethod.GET)
	@GetMapping("/created.action")
	public ModelAndView created() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("bbs/created");
		
		return mav;
		
	}
	
	
	///"created.action"으로 들어오는 요청은 Post방식으로 처리
	//파라미터로 dto와 request를 받아옴
	//@RequestMapping(value = "/created.action", method = RequestMethod.POST)
	@PostMapping("/created.action")
	public ModelAndView created_ok(BoardDTO dto, HttpServletRequest request)
			throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		//boardService에서 maxNum 데이터를 불러와 담음
		int maxNum = boardService.maxNum();
		
		//불러온 maxNum 데이터에 +1을 해 dto.setter에 넘김
		//아이피주소를 요청해 dto.setter에 넘김
		dto.setNum(maxNum+1);
		dto.setIpAddr(request.getRemoteAddr());
		
		//boardService.insertData에 dto를 넘김
		//dto에 num과 ipAddr 데이터가 넘겨짐
		boardService.insertData(dto);
		
		//데이터가 다 넘어가면 처리 완료
		//post방식이기 때문에 게시판을 보여주는 페이지 리다이렉트
		mav.setViewName("redirect:/list.action");
		
		return mav;
		
	}
	
	
	//Get 방식으로 "/list.action"로 들어오는 모든 요청 처리
	//list에 파라미터로 HttpServletRequest 넘겨줌
	//@RequestMapping(value = "/list.action",
	//		method = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/list.action")
	public ModelAndView list(HttpServletRequest req) throws Exception{


		/*페이징 처리*/
		//pageNum 파라미터를 가져와서 pageNum 변수에 담음
		String pageNum = req.getParameter("pageNum");

		//현재 페이지를 1로 설정
		int currentPage = 1;

		//넘어온 pageNum 데이터가 null이 아니면 Int로 변환해
		//currentPage에 담음
		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}

		//---------------------------------------------------------------------------
		//searchKey, searchValue 파라미터를 가져와 각각의 변수에 담음
		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");

		//검색한 값이 없으면
		//searchKey에는 subject를 searchValue에는 null값을 담음
		if(searchValue==null) {
			searchKey = "subject";
			searchValue = "";
		}else {
			//검색한 값이 있으면
			//메소드를 요청해 대소문자 구별없이 "get"과 비교하고
			//get방식이면 searchValue값(한글)이 깨지지 않게 UTF-8로 디코딩 
			if(req.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}

		//---------------------------------------------------------------------------
		
		//seachKey와 searchValue 값을 boardService.getDataCount에 넘겨주어 dataCount에 담음
		int dataCount = boardService.getDataCount(searchKey, searchValue);

		//페이지 당 게시글은 5개
		//myUtil.getPageCount에 numPerPage와 dataCount를 넘겨주고 받은 연산결과를 totalPage에 담음
		int numPerPage = 5;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		//현재 페이지가 totalPage보다 크면
		//currentPage와 totalPage를 같게 함
		if(currentPage>totalPage) {
			currentPage = totalPage;
		}

		//첫페이지 인덱스와 마지막 페이지 인덱스 연산
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;

		//boardService.getLists에 매개변수를 넘겨서 받은 데이터를 lists에 담음
		List<BoardDTO> lists = 
				boardService.getLists(start, end, searchKey, searchValue);

		//검색 값이 있으면 변수명과 데이터를 이어 붙여 param에 넘겨줌
		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");		
		}

		
		String listUrl = "/list.action";

		//param에 데이터가 있으면 생성해 놓은 listUrl에 데이터를 붙여
		//주소를 형성해 줌
		if(!param.equals("")) {				
			listUrl += "?" + param;				
		}

		
		String pageIndexList =
				myUtil.pageIndexList(currentPage, totalPage, listUrl);

		String articleUrl = "/article.action?pageNum=" + currentPage;

		if(!param.equals("")) {
			articleUrl += "&" + param;
		}

		ModelAndView mav = new ModelAndView();
		
		//포워딩할 데이터
		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("pageNum",currentPage);

		mav.setViewName("bbs/list");

		return mav;

	}
	
	
	//@RequestMapping(value = "/article.action",method = RequestMethod.GET)
	@GetMapping("/article.action")
	public ModelAndView article(HttpServletRequest request) throws Exception{

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		//searchValue는 디코딩작업을 해줘야 함..
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		boardService.updateHitCount(num);
		
		BoardDTO dto = boardService.getReadData(num);
		
		//dto가 null이면(해당 데이터가 없으면) atricle이 아니라 다시 list로 넘겨줘야 함
		if(dto==null) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			
			return mav;
			
		}
		
		int lineSu = dto.getContent().split("\n").length;
		
		//.replaceAll()은 String에서만 쓸 수 있음. content의 타입이 String이기 때문에 쓸 수 있는 것
		//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		String param = "pageNum=" + pageNum;
		
		//String의 null값은 두번 체크해서 완벽하게 걸러낼수 있도록 해줌.
		//단, 두 조건의 순서가 바뀌어서는 안된다.
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
	
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("lineSu", lineSu);
		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("bbs/article");
		
		return mav;
		
		
	}
	
	
//	@RequestMapping(value = "/updated.action",
//			method = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/updated.action")
	public ModelAndView updated(HttpServletRequest request) throws Exception{

		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		BoardDTO dto = boardService.getReadData(num);
		
		if(dto==null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			
			return mav;
			
		}
		
		String param = "pageNum=" + pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);
		
		mav.setViewName("bbs/updated");
		
		return mav;
		
		
	}	
		
		
//	@RequestMapping(value = "/updated_ok.action",
//			method = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/updated_ok.action")
	public ModelAndView updated_ok(BoardDTO dto,HttpServletRequest request) throws Exception{

		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
	
		boardService.updateData(dto);
		
		String param = "pageNum=" + pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav;
		
	}
	
	
	
//	@RequestMapping(value = "/deleted_ok.action", 
//			method = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/deleted_ok.action")
	public ModelAndView deleted_ok(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		boardService.deleteData(num);
		
		String param = "pageNum=" + pageNum;

		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav;		
	}
	
	
	
}
