package com.springexample.springmvc5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping(path = "/demo/")
public class DemoController {
	
	@RequestMapping(path = "/galleryimageupload")
	public String imageUpload(MultipartFile Filedata, String callback, String callback_func, HttpServletRequest req) throws Exception {
		
		String return1 = callback;
		String return2 = "?callback_func=" + callback_func;
		String return3 = "";
		String fileName = "";
		
		if (Filedata != null) {
					
			fileName = Filedata.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".")+1);
            //파일 기본경로
            String defaultPath = req.getServletContext().getRealPath("/");
            //파일 기본경로 _ 상세경로
            String path = defaultPath + "resources" + File.separator + "upload" + File.separator;
             
            File file = new File(path);
             
            //디렉토리 존재하지 않을경우 디렉토리 생성
            if(!file.exists()) {
                file.mkdirs();
            }
            
            //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
            String realname = UUID.randomUUID().toString() + "." + ext;
            ///////////////// 서버에 파일쓰기 ///////////////// 
            Filedata.transferTo(new File(path + realname));

            return3 += "&bNewLine=true&sFileName="+fileName+"&sFileURL=/spring-mvc-richeditor/resources/upload/"+realname;
        }else {
            return3 += "&errstr=error";
        }
		
		return "redirect:" + return1+return2+return3;
	}
	
	@RequestMapping(path = "/galleryimageupload2")
	@ResponseBody
	public String imageUpload2(HttpServletRequest req) throws Exception {
		String sFileInfo = "";
		//파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴 
		String name = req.getHeader("file-name");
		String ext = name.substring(name.lastIndexOf(".")+1);
		//파일 기본경로
		String defaultPath = req.getServletContext().getRealPath("/");
		//파일 기본경로 _ 상세경로
		String path = defaultPath + "upload" + File.separator;
		File file = new File(path);
		if(!file.exists()) {
		    file.mkdirs();
		}
		String realname = UUID.randomUUID().toString() + "." + ext;
		InputStream is = req.getInputStream();
		OutputStream os=new FileOutputStream(path + realname);
		int numRead;
		// 파일쓰기
		byte b[] = new byte[Integer.parseInt(req.getHeader("file-size"))];
		while((numRead = is.read(b,0,b.length)) != -1){
		    os.write(b,0,numRead);
		}
		if(is != null) {
		    is.close();
		}
		os.flush();
		os.close();
		sFileInfo += "&bNewLine=true&sFileName="+ name+"&sFileURL="+"/spring-mvc-richeditor/upload/"+realname;
		
		return sFileInfo;
		
	}
	
	@RequestMapping(path = { "/write" })
	@ResponseBody
	public String write(String title, String writer, String smarteditor) {
		
		System.out.println(title);
		System.out.println(writer);
		System.out.println(smarteditor);
		return "success";
		
	}

	
	
}









