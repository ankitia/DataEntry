package com.ia.Dao;

import java.util.List;

import com.ia.modal.DataEntryList;
import com.ia.modal.User;
import com.ia.modal.UserDetail;

public interface HomeDao {

	List<String> getData();
	
	User checkValidUser(String userName,String password);
	
	boolean insert(User user);
	
	List<User> getUserList();
	
	List<UserDetail> getUserDetails();
	
	boolean insertlist();
	
	int getTotalCount(int userId,String action);
	
	boolean updateUrlStatus(long urlId,String status,String action);
	
	/*boolean updateProfileUrlStatus(long urlId,String status);*/
	
	String getQueryTime(String action,String totalHour,int userId);
	
	String getActiveUsers();
	
	boolean updateLinkScore(int userId,String total,String action);

	boolean setPendingLink(String action,int userId,int limit);
	
	 
	boolean reActiveMasterURL(String ids,String action);
 	
	List<DataEntryList> exportDataEntry(String startDate,String endDate,String action);
}
