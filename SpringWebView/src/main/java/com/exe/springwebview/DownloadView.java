package com.exe.springwebview;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		//1. 브라우저에게 처리할 수 없는 컨텐츠로 알려줘서 다운로드하도록 지정
		//(octet으로 처리한 파일)
		//response.setContentType("application/octet-stream");
		response.setContentType("application/unknown");
		
		
		//2. 다운로드 처리할 때 필요한 정보 제공
		response.addHeader("content-Disposition", 
				"attachment;FileName=\"java.zip\"");
		
		//3.파일을 응답 스트림에 기록
		String path = request.getSession()
				.getServletContext().getRealPath("/WEB-INF/files/java.zip");
		
		
		/*
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		int data;
		
		while((data=bis.read())!=-1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
		*/
		
		FileInputStream fis = new FileInputStream(path);
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fis, os);
		
		fis.close();
		os.close();
		
		
		
	}

}
