package com.ia.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.ia.Dao.HomeDao;
import com.ia.modal.DataEntryList;
import com.ia.modal.User;

@Controller
public class HomeController {

	@Autowired	 
	HomeDao homeDao;
	
	 
	@RequestMapping(value="/")
	public String home(Model model,HttpSession session) {
		
		try {
			if(new SimpleDateFormat("MM/dd/yyyy").parse("01/13/2019").after(new SimpleDateFormat("MM/dd/yyyy").parse("01/11/2019"))) {
	        	 System.out.println("Helo---------------");
	         }
			
			if(new SimpleDateFormat("MM/dd/yyyy").parse("01/12/2019").before(new SimpleDateFormat("MM/dd/yyyy").parse("05/12/2019"))) {
	        	 System.out.println("Helo-------sdsdsds-------");
	         }
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(session.getAttribute("userRole")!=null) {
			if(session.getAttribute("userRole").toString().equalsIgnoreCase("1")) {
				 return "redirect:admindashboard";
			 }else {
				 return "redirect:masterDataEntryURL";
			 }	
		}
		//return "front/index";
		return "admin/login";
	}
	
	@RequestMapping(value="checkValidUser")
	@ResponseBody public String checkValidUser(HttpServletRequest request,HttpSession session) {
		
		User user = homeDao.checkValidUser(request.getParameter("password"),request.getParameter("userPassword"));
		
		if(user!=null) {
			
			session.setAttribute("username", user.getFname() +" "+ user.getLname());
			
			return "true";
		}else		
			return  "false";
		
	}
 
	
	@RequestMapping(value="signout")
	public String signout(HttpSession session)
	{
		
		session.setAttribute("username", null);
		return "redirect:/";
				
				
	}
	
	
	
	 

	/* Start Admin controller */
	@RequestMapping(value="login")
	public String login()
	{
		System.out.println("This is callllllllllllllllllllllllllllllll");
		return "admin/login";
	}
	
	@RequestMapping(value = "/updateUrlStatus")
	@ResponseBody String updateUrlStatus(HttpServletRequest request) {
		
		String action = request.getParameter("action");
		 if(action.equalsIgnoreCase("DataEntry")){
			return  homeDao.updateUrlStatus(Long.parseLong(request.getParameter("urlId")), request.getParameter("status"),"DataEntry")+"";
		}else if(action.equalsIgnoreCase("EmailEntry")){
			return  homeDao.updateUrlStatus(Long.parseLong(request.getParameter("urlId")), request.getParameter("status"),"EmailEntry")+"";
		}
		
		return "false";
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST )
	public String checkUser(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response)  {
		
		System.out.println(request.getParameter("Email")+"---------"+request.getParameter("password"));
		 User user = homeDao.checkValidUser(request.getParameter("Email"), request.getParameter("password"));
		 
		 System.out.println("User ::"+user.getUserId());
		 
		 if(user!=null && user.getUserId()>0) {		 
			 session.setAttribute("userName", request.getParameter("Email"));
			 session.setAttribute("userId", user.getUserId());
			 session.setAttribute("userRole", user.getUserRole());
			 session.setAttribute("approvedLink", user.getApprovedLink());
			 session.setAttribute("approvedLink2", user.getApprovedLink2());
			 /*session.setAttribute("approvedLink3", user.getApprovedLink3());*/
			 session.setAttribute("companyLink", user.getCompanyLink()); 
			 System.out.println("Login user Id::"+ user.getUserId());
			 
			 Cookie ck=new Cookie("userId",user.getUserId()+"");//creating cookie object  
			 response.addCookie(ck);//adding cookie in the response  

			 
			 String userRole = user.getUserRole();
			 System.out.println(user +"--------------");
			 if(userRole.equalsIgnoreCase("1")) {
				 return "redirect:admindashboard";
			 }else {
				 
			 }
			 
			 return "redirect:masterDataEntryURL";
		 }else {
			
			model.addAttribute("message", "Please enter valid username and password");
			return "admin/login";
		}		
	}
	
	
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session)
	{
		session.setAttribute("userName", null);
		session.setAttribute("userId", null);
		session.setAttribute("userRole", null);
		 
		return "admin/login";
	}
	
	 
	
	
	@RequestMapping(value="admindashboard")
	public String admindashboard(HttpServletRequest requestm,Model model)
	{
		//model.addAttribute("userList", homeDao.getUserDetails());		
		System.out.println("This is admin dashboard -- ");
		return "admin/admindashboard";
	}
	
	@RequestMapping(value="userAssigned-ia")
	public String userAssigned(HttpServletRequest requestm,Model model)
	{
		model.addAttribute("userList", homeDao.getUserList());		
		return "admin/user_assigned";
	}
 
	
	
	
	@RequestMapping(value="setPendingLink")
	@ResponseBody public String getUserActiveLink(HttpServletRequest request) {
		
		String dataProcess = request.getParameter("dataProcess");
		String action = request.getParameter("action");
		if(dataProcess.equalsIgnoreCase("userProfile")) {
			
			if(action.equalsIgnoreCase("reset")) {
				action = "resetScrap";
			}else {
				action = "assignScrap";
			}			
			
		}else if(dataProcess.equalsIgnoreCase("companyData")) {
			
			if(action.equalsIgnoreCase("reset")) {
				action = "resetCompany";
			}else {
				action = "assignCompany";
			}
		}
		
		System.out.println("action------------"+action);
		
		return homeDao.setPendingLink(action, Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("limit")))+"";
	}
	
 
	
	  
	
	@RequestMapping(value="report")
	public String createreport(HttpServletRequest request,HttpServletResponse response) {
		
		return "admin/adminreport";
	}
	
	@RequestMapping(value="resetLinks")
	@ResponseBody public String resetLinks(HttpServletRequest request) {
		
		String action = request.getParameter("reportList");
		System.out.println(request.getParameter("masterIds")+"--"+action);
		return homeDao.reActiveMasterURL(request.getParameter("masterIds"), action)+"";	
		
	}
	
	
	@RequestMapping(value="downloadReport")
	@ResponseBody public void downloadReport(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println(request.getParameter("action") +"--"+request.getParameter("exportStartDate")+" --- "+request.getParameter("exportEndDate"));
		
	   String csvFileName = "Export_Data.csv";
	   String headerKey = "Content-Disposition";
       String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
       response.setHeader(headerKey, headerValue);
	  
       // uses the Super CSV API to generate CSV data from the model data
       ICsvBeanWriter csvWriter;
       String action = request.getParameter("action");
    	   try {
   			csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
   			String[] header = { "dataEntryId","firstName","jobTitle","nameSource","contactEmail","contactEmailSource","contactPhone","contactPhoneSource","companyName","companyNameSource","companyWebsite","companyWebsiteSource","companyEmail","companyEmailSource","companyPhone","companyPhoneSource","propertyName","propertyWebsite","propertySource","user_id","ipaddress","contactUsFormLink","remarks","address","city","state","zipCode","url","createdDate"};
   	        csvWriter.writeHeader(header);
   	        List<DataEntryList> tempUrls = homeDao.exportDataEntry(request.getParameter("exportStartDate"),request.getParameter("exportEndDate"),action);
   	        for (DataEntryList aBook : tempUrls) {
   	            csvWriter.write(aBook, header);
   	        }
   	     csvWriter.close();
			
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
	}
	
	/* End Admin controller */
	
	 
}
	