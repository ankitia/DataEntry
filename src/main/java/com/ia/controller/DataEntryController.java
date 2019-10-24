package com.ia.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ia.Dao.DataEntryDao;
import com.ia.Dao.HomeDao;
import com.ia.modal.DataEntry;

@Controller
public class DataEntryController {
	
	@Autowired
	DataEntryDao dataEntryDao; 
	 
	
	@Autowired
	HomeDao homeDao;
	
	@RequestMapping(value="masterDataEntryURL")
	public String masterDataEntryURL(HttpServletRequest reques,Model model,HttpSession session)
	{
		if(session.getAttribute("userId")==null)
			return "redirect:login";
	
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		model.addAttribute("urlList",dataEntryDao.getDataEntryUrlList(userId,"display"));
		model.addAttribute("userVerificationActive",dataEntryDao.getDataEntryUrlList(userId,"active").size());
		model.addAttribute("getTotalActiveLink",dataEntryDao.getDataEntryUrlList(0, "active").size());
		
		if(dataEntryDao.getDataEntryUrlList(userId,"display").size()>0)
			model.addAttribute("dataEntryList", dataEntryDao.getDataEntryByUrlId(Integer.parseInt(dataEntryDao.getDataEntryUrlList(userId,"display").get(0).getUrlId()+"")));
		return "admin/data_entry_url"; 
	}
	
	@RequestMapping(value="getKFBDailyCount" , method=RequestMethod.POST)
	@ResponseBody public HashMap<String, Integer> getKFBDailyCount(HttpSession session) {
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		HashMap<String, Integer> hashMap = new HashMap<>(); 
		hashMap.put("userVerificationApproved",homeDao.getTotalCount(userId,"dataEntryDailyCount"));
		hashMap.put("userVerificationContacts",homeDao.getTotalCount(userId,"dataEntryDailyContactCount"));
		hashMap.put("userVerificationEmails",homeDao.getTotalCount(userId,"dataEntryDailyEmailCount"));
		return hashMap;
	}
	
	@RequestMapping(value="insertDataEntry", method=RequestMethod.POST)
 	public String insertDataEntry(DataEntry dataEntry,HttpServletRequest request,HttpSession session) 
	{
		
		int chkContactFound=0,chkContactUsForm=0,chkSiteNotValid=0,chkSiteDown=0;
		
		dataEntry.setChkContactFound("1");
		dataEntry.setChkContactUsForm("1");
		dataEntry.setChkSiteDown("1");
		dataEntry.setChkSiteNotValid("1");
		
		
		
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		
		dataEntry.setUser_id(userId+"");
		
		if(dataEntryDao.getDataEntryUrlList(userId,"display").get(0).getUrlId()>0)
			dataEntry.setUrl_id(dataEntryDao.getDataEntryUrlList(userId,"display").get(0).getUrlId()+"");
		dataEntry.setIpaddress(request.getRemoteAddr());
	        
	       int bingId = dataEntryDao.insertDataEntry(dataEntry);
	        return "redirect:masterDataEntryURL";
	}
	
	@RequestMapping(value="dataEntryVerificationLog")
	public String dataEntryVerificationLog(HttpServletRequest requestm,Model model,HttpSession session)
	{
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		
		
		model.addAttribute("total",homeDao.getTotalCount(userId,"dataEntry"));		
		model.addAttribute("getScrap", dataEntryDao.getDataEntry(userId));		
		
		System.out.println("This is dataEntryVerificationLog  "+userId);
		return "admin/data_entry_verification_log";
	}
	
	@RequestMapping(value="dataEntryVerificationMissed")
	public String dataEntryVerificationMissed(Model model,HttpSession session)
	{ 
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		model.addAttribute("urlList",dataEntryDao.getDataEntryUrlList(userId,"missed"));	
		System.out.println("This is data_entry_verification_missed  "+userId);
		return "admin/data_entry_verification_missed";
	}
	
	@RequestMapping(value="getMoreLinks")
	@ResponseBody public String getMoreLinks(HttpSession session,@RequestParam("tableName") String action) {
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		if(action.equalsIgnoreCase("assignDataEntry")) { 
			return homeDao.setPendingLink("assignDataEntry", userId, 1)+"";
		}else if(action.equalsIgnoreCase("assignEmailEntry")) { 
			return homeDao.setPendingLink("assignEmailEntry", userId, 1)+"";
		}
		
		
		return "";
				
	}

}
