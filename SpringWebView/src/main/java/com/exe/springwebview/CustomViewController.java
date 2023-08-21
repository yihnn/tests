package com.exe.springwebview;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomViewController {

	@RequestMapping(value = "/simpleCustomView.action")
	public ModelAndView customView() {

		ModelAndView mav = new ModelAndView();

		//mav.setViewName("simpleCustomView                                            ");	//view->jsp���Ϸ� �� ��
		mav.setView(new SimpleCustomView());
		mav.addObject("message", "Simple Custom View Class��~");	//view->class


		return mav;

	}



	@RequestMapping(value = "/pdfView.action")
	public ModelAndView getPdfView() {

		ModelAndView mav = new ModelAndView();

		//mav.setViewName("simpleCustomView                                            ");	//view->jsp���Ϸ� �� ��
		mav.setView(new CustomPdfView());
		mav.addObject("message", "PDF File�̿�~");	//view->class


		return mav;

	}



	@RequestMapping(value = "/excelView.action")
	public ModelAndView getExcelView() {

		ModelAndView mav = new ModelAndView();

		//mav.setViewName("simpleCustomView                                            ");	//view->jsp���Ϸ� �� ��
		mav.setView(new CustomExcelView());


		return mav;

	}


	@RequestMapping(value = "/upload.action", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) 
			throws Exception{

		String path = request.getSession()
				.getServletContext().getRealPath("/WEB-INF/files");

		MultipartFile file = request.getFile("upload");

		if(file!=null && file.getSize()>0) {

			try {
				InputStream is = file.getInputStream();

				FileOutputStream fos = 
						new FileOutputStream(path + "/" + file.getOriginalFilename());

				/*
				int data;
				byte[] buffer = new byte[4096];
				while((data=is.read(buffer, 0, buffer.length))!=-1) {
					fos.write(buffer,0,data);
				}
				 */

				//�������� ��ü������ ���� ���ε� Ŭ������ �־ ������
				// �Ʒ� �ڵ��� �����
				FileCopyUtils.copy(is, fos);

				is.close();
				fos.close();


			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "uploadResult";

	}

	
	@RequestMapping(value = "download.action")
	public ModelAndView download() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setView(new DownloadView());
		
		return mav;
		
		
	}

}
