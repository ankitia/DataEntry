package com.ia.Dao;

import java.util.List;

import com.ia.modal.DataEntry;
import com.ia.modal.EmailData;
import com.ia.modal.MasterDataEntryURL;
import com.ia.modal.MasterEmail;

public interface EmailEntryDAO {
	
	//Get email list
	public List<MasterEmail> getEmailData(int status);
	
	//Get all EmailData
	public int insertEmailData(EmailData emailData);
	
	public int insertEmailEntry(DataEntry dataEntry);
	
	List<MasterDataEntryURL> getEmailEntryUrlList(int userId,String action);
	
	List<DataEntry> getEmailEntry(int userId);
	List<DataEntry> getEmailEntryByUrlId(int urlId);
	
	EmailData getEmailDataByUrlId(int urlId);
	
	
 }
