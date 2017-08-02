package com.lester.core.action;

import com.lester.core.dao.impl.UserDaoImpl;
import com.lester.core.model.TestBean;
import com.lester.support.handler.ActionSupport;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestController extends ActionSupport{

	@Autowired
	private TestBean testBen;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@RequestMapping(value = "/song.action", method = RequestMethod.GET)
    public void index_jsp(Model model){  
//        model.addAttribute("ORZ", "囧");  
        System.out.println(testBen.XXX);
    }
	
//	@RequestMapping(value = "/testAjax.action", method = RequestMethod.POST)	
//	public @ResponseBody String saveCompany_JSON(@RequestBody String req) throws Exception{
//		List<CfgAuthUser> users = userDao.queryUsers(new Pagination(1, 5));
//		return jsonRes(users);
//	}
	
	/*
	 * 采用file.Transto 来保存上传的文件
	 */
	@RequestMapping("/testUpload.action")
	public @ResponseBody String fileUpload2(@RequestParam("file") CommonsMultipartFile file, @RequestParam("ticketId") Long ticketId) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "C:\\Users\\et06060606\\Desktop\\新增資料夾\\" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);

		newFile.mkdirs();
		
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "200";
	}
	
	/*
	 * 采用file.Transto 来保存上传的文件
	 */
	@RequestMapping("/uploadFile.action")
	public @ResponseBody String uploadFile(
			@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("ticketId") Long ticketId,
			@RequestParam("fileType") Long fileType,
			@RequestParam("remark") String remark) throws Exception {
		
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "C:\\Users\\et06060606\\Desktop\\新增資料夾\\" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);

		newFile.mkdirs();
		
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "200";
	}
	
	@RequestMapping(value = "/fileDownload.action", method = RequestMethod.POST)
	public void fileDownload(HttpServletResponse response, @RequestParam("fileId") Long fileId) throws IOException {
			String filePath = "C:\\Users\\et06060606\\Desktop\\XXX\\YEAR_2016\\MONTH_6\\TICKE_ID_54\\STORE_TIME_20160716235542\\檔案_000.jpeg";

			InputStream is = new FileInputStream(filePath);

//	        response.setLocale(java.util.Locale.TAIWAN);
//	        response.setContentType("application/pdf;charset=UTF-8;");
	        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("檔案_000.jpeg", "utf-8"));
	        
	        
////	        // for IE8 https download start
//	        response.setHeader("Cache-Control", "private");
//	        response.setHeader("Pragma", "private");
////	        // for IE8 https download end
	           
	        OutputStream os = response.getOutputStream();
	        byte[] pdfByte = IOUtils.toByteArray(is);
	        os.write(pdfByte);
	        os.close();
			is.close();
		}
}
