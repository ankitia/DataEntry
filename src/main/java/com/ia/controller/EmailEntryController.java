package com.ia.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ia.Dao.EmailEntryDAO;
import com.ia.Dao.HomeDao;
import com.ia.modal.DataEntry;
import com.ia.modal.MasterEmail;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@Controller
public class EmailEntryController {
	
	@Autowired
	EmailEntryDAO emailEntryDao; 
	 
	
	@Autowired
	HomeDao homeDao;
	
	@RequestMapping(value="masterEmailEntryURL")
	public String masterEmailEntryURL(HttpServletRequest reques,Model model,HttpSession session)
	{
		if(session.getAttribute("userId")==null)
			return "redirect:login";
	
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		model.addAttribute("urlList",emailEntryDao.getEmailEntryUrlList(userId,"display"));
		model.addAttribute("userVerificationActive",emailEntryDao.getEmailEntryUrlList(userId,"active").size());
		model.addAttribute("userVerificationApproved",homeDao.getTotalCount(userId,"emailEntry"));
		model.addAttribute("userVerificationAll",emailEntryDao.getEmailEntryUrlList(userId,"all").size());
		model.addAttribute("getTotalActiveLink",emailEntryDao.getEmailEntryUrlList(0, "active").size());
		if(emailEntryDao.getEmailEntryUrlList(userId,"display").size()>0)
			model.addAttribute("dataEntryList", emailEntryDao.getEmailEntryByUrlId(Integer.parseInt(emailEntryDao.getEmailEntryUrlList(userId,"display").get(0).getUrlId()+"")));
		
		return "admin/email_entry_url";
	}
	
	@RequestMapping(value="insertEmailEntry", method=RequestMethod.POST)
 	public String insertEmailEntry(DataEntry dataEntry,HttpServletRequest request,HttpSession session) 
	{
		if(session.getAttribute("userId")==null)
			return "redirect:login";
		//int chkContactFound=0,chkContactUsForm=0,chkSiteNotValid=0,chkSiteDown=0;
		
		dataEntry.setChkContactFound("1");
		dataEntry.setChkContactUsForm("1");
		dataEntry.setChkSiteDown("1");
		dataEntry.setChkSiteNotValid("1");
		
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		dataEntry.setUser_id(userId+"");
		
		if(emailEntryDao.getEmailEntryUrlList(userId,"display").get(0).getUrlId()>0)
			dataEntry.setUrl_id(emailEntryDao.getEmailEntryUrlList(userId,"display").get(0).getUrlId()+"");
		dataEntry.setIpaddress(request.getRemoteAddr());
	        
	       emailEntryDao.insertEmailEntry(dataEntry);
	        return "redirect:masterEmailEntryURL";
	}
	
	@RequestMapping(value="emailEntryVerificationLog")
	public String emailEntryVerificationLog(HttpServletRequest requestm,Model model,HttpSession session)
	{
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		
		
		model.addAttribute("total",homeDao.getTotalCount(userId,"emailEntry"));		
		model.addAttribute("getScrap", emailEntryDao.getEmailEntry(userId));		
		
		System.out.println("This is dataEntryVerificationLog  "+userId);
		return "admin/email_entry_verification_log";
	}
	
	@RequestMapping(value="emailEntryVerificationMissed")
	public String emailEntryVerificationMissed(Model model,HttpSession session)
	{ 
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		model.addAttribute("urlList",emailEntryDao.getEmailEntryUrlList(userId,"missed"));	
		System.out.println("This is getEmailEntryUrlList missed  "+userId);
		return "admin/email_entry_verification_missed";
	}
	

	@RequestMapping(value="getEmailDataByUrlId" , method =RequestMethod.GET)
	public String getEmailDataByUrlId(int urlId,Model model) throws IOException, MessagingException, javax.mail.MessagingException {
		
		System.out.println("urlId----"+urlId);
		
		model.addAttribute("emailData",emailEntryDao.getEmailDataByUrlId(urlId).getContent());
		
		//return SendSMS.toString(emailEntryDao.getEmailDataByUrlId(urlId).getContent()+"");
		return "admin/email_data_show";
	}
	
	@RequestMapping(value="/insertEmailData")
	@ResponseBody public String insertEmailData(Model model) {
		 
		List<MasterEmail> emails = emailEntryDao.getEmailData(0);
		System.out.println(emails.size());
			for (int i = 0; i < emails.size(); i++) {
				
				System.out.println("Email ::-->>"+emails.get(i).getEmailId()+"----"+emails.get(i).getPassword());
				
				EmailConfig.check(emails.get(i).getHost(), "pop3", emails.get(i).getEmailId(), emails.get(i).getPassword(),emailEntryDao,emails.get(i).getId());
			}
		 return "project";
	 }

}
