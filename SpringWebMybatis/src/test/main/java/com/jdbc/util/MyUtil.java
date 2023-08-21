package com.jdbc.util;

//Pageó��
public class MyUtil {
	
	//��ü �������� ����
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount%numPerPage != 0) {
			pageCount++;
		}
		
		return pageCount;
		
	}
	
	//����¡ ��ũ
	public String pageIndexList(int currentPage,int totalPage, String listUrl) {
		
		int numPerBlock = 5;
		int currentPageSetup;
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) {
			return "";
		}
		
		//listUrl
		//list.jsp--?pageNum=3
		//list.jsp?searchKey=subject&searchValue=suzi--&pageNum=3
		if(listUrl.indexOf("?")!=-1) {			
			listUrl = listUrl + "&";			
		}else {
			listUrl = listUrl + "?";
		}
		
		//���� ������ ��ȣ ����
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup-numPerBlock;
		}
		
		
		//������
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					currentPageSetup + "\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">������</a>&nbsp;
			
		}
		
		//�ٷΰ��� ������
		page = currentPageSetup + 1;
		
		while(page<=totalPage && page<=(currentPageSetup+numPerBlock)) {
			
			if(page == currentPage) {
				
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				//<font color="Fuchsia">6</font>&nbsp;
				
			}else {
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" +
						page + "</a>&nbsp;");
				//<a href="list.jsp?pageNum=7">7</a>&nbsp;
				
			}			
			
			page++;
			
		}
		
		//������
		if(totalPage-currentPageSetup>numPerBlock) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + 
					"\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">������</a>&nbsp;
			
		}
		
		return sb.toString();		
		
	}
	

}































