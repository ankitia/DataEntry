
package com.ia.IMPL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.Dao.HomeDao;
import com.ia.modal.DataEntryList;
import com.ia.modal.User;
import com.ia.modal.UserDetail;

@Component("homeDao")
public class HomeImpl implements HomeDao {

	@Autowired
	DataSource dataSource;
		
	Connection con;
	
	@Override
	public List<String> getData() {
		// TODO Auto-generated method stub
			List<String> data = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "select * from login";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("username"));
				data.add(rs.getString("username"));
				
			}
			System.out.println("Size:: "+rs.last());
			System.out.println("Size:: "+rs.getRow());
			 con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
		return data;
	}

	@Override
	public User checkValidUser(String userName, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "select * from user where useremail = ? and binary password = ?";
			PreparedStatement  ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserRole(rs.getString("user_role"));
				user.setApprovedLink(rs.getString("approved_link"));
				user.setApprovedLink2(rs.getString("approved_link_scrap2"));
				user.setApprovedLink3(rs.getString("approved_link_scrap3"));
				user.setCompanyLink(rs.getString("company_link"));
			} 
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "insert into user(fname,lname,useremail,password) value(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, user.getFname());
			ps.setString(2, user.getLname());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getPassword());
			status = ps.executeUpdate();
			con.close();	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("Status :::"+status);
		if(status > 0)
			return true;
		else		
			return false;
	}

 
	
	 
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "select * from user";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setUserEmail(rs.getString("useremail"));
				user.setPassword(rs.getString("password"));
				user.setMobileNumber(rs.getString("mobile_number"));
				user.setUserId(rs.getInt("user_id"));
				users.add(user);
			} 
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ::::");
			
			e.printStackTrace();
		}
		return users;
	}
	
	 
	@Override
	public boolean insertlist() {
		// TODO Auto-generated method stub
		
		return false;
	}

	 
	
	@Override
	public int getTotalCount(int userId,String action) {
		// TODO Auto-generated method stub
			int totalRecord = 0;
		try(Connection con = (Connection) dataSource.getConnection()) {
			
			String sql = "";
			if(action.equalsIgnoreCase("dataEntry")) {
				//sql = "select count(distinct(url_id)) as total from data_entry where user_id = ?";	
				sql = "select count(url_id) as total from data_entry where user_id = ?";
			}else if(action.equalsIgnoreCase("emailEntry")) {
				sql = "select count(distinct(url_id)) as total from email_entry where user_id = ?";	
			}else if(action.equalsIgnoreCase("dataEntryDailyCount")) {
				sql = "SELECT count(url_id) as total from data_entry where DATE_FORMAT(convert_tz(now(),@@session.time_zone,'+05:30') ,'%Y-%m-%d') = DATE_FORMAT(CONVERT_TZ(created_date, @@session.time_zone, '+05:30') ,'%Y-%m-%d') and user_id = ?";
			}else if(action.equalsIgnoreCase("dataEntryDailyContactCount")) {
				sql = "SELECT count(url_id) as total from data_entry where DATE_FORMAT(convert_tz(now(),@@session.time_zone,'+05:30') ,'%Y-%m-%d') = DATE_FORMAT(CONVERT_TZ(created_date, @@session.time_zone, '+05:30') ,'%Y-%m-%d') and user_id = ? and first_name != ''";
			}else if(action.equalsIgnoreCase("dataEntryDailyEmailCount")) {
				sql = "SELECT count(url_id) as total from data_entry where DATE_FORMAT(convert_tz(now(),@@session.time_zone,'+05:30') ,'%Y-%m-%d') = DATE_FORMAT(CONVERT_TZ(created_date, @@session.time_zone, '+05:30') ,'%Y-%m-%d') and user_id = ? and (contact_email != '' or company_email != '')";
			}
			
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				totalRecord =  rs.getInt("total");
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return totalRecord;
	}
	
	
	 

	@Override
	public List<UserDetail> getUserDetails() {
		// TODO Auto-generated method stub
		List<UserDetail> users = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "select u.fname,u.lname,u.useremail,u.mobile_number,u.password,count(s.user_id) as total from scrap s,user u where u.user_id=s.user_id  group by  s.user_id";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserDetail user = new UserDetail();
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setUserEmail(rs.getString("useremail"));
				user.setPassword(rs.getString("password"));
				user.setMobileNumber(rs.getString("mobile_number"));
				user.setTotal(rs.getString("total"));
				users.add(user);
			} 
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ::::");
			
			e.printStackTrace();
		}
		return users;
	}

	 

	@Override
	public boolean updateUrlStatus(long urlId, String status,String action) {
		// TODO Auto-generated method stub
		int queryStatus = 0;
		try(Connection con = (Connection) dataSource.getConnection()) {
			String sql = "";
			if(action.equalsIgnoreCase("DataEntry")) {
				sql = "update  master_data_entry_url set status = ? where master_data_id = ?";
			}else if(action.equalsIgnoreCase("EmailEntry")) {
				sql = "update  email_data set status = ? where email_data_id = ?";
			} 
			
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setLong(2, urlId);
			queryStatus = ps.executeUpdate();
			con.commit();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println(urlId+"--Status :::"+queryStatus+"---"+action+"---"+status);
		if(queryStatus > 0)
			return true;
		else		
			return false;
	}

	 
	@Override
	public String getQueryTime(String action,String totalHour,int userId) {
		String time = "";
		try(Connection con = (Connection) dataSource.getConnection()) {
			String sql = "";
			PreparedStatement ps = null;
			if(action.equalsIgnoreCase("scrap")) {
				sql = "select count(*) as cnt from  scrap where created_date >= DATE_SUB(NOW(),INTERVAL ? HOUR) and user_id = ?";				
			}else if(action.equalsIgnoreCase("listContacts")) {
				sql = "select count(*) as cnt from  list_contacts where created_date >= DATE_SUB(NOW(),INTERVAL ? HOUR) and user_id = ?";
			}else if(action.equalsIgnoreCase("companyData")) {
				sql = "select count(*) as cnt from  company_detail where created_date >= DATE_SUB(NOW(),INTERVAL ? HOUR) and user_id = ?";
			}else if(action.equalsIgnoreCase("listBuild")) {
				sql = "select count(*) as cnt from  list_building_details where created_date >= DATE_SUB(NOW(),INTERVAL ? HOUR) and user_id = ?";
			}
			
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, totalHour);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				time = rs.getString("cnt");
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return time;
	}

	 

	@Override
	public String getActiveUsers() {
		String userIds="";
		try {
			Connection con = (Connection) dataSource.getConnection();
			String sql = "select group_concat(user_id) as user_id from user where status = 'Active'";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userIds = rs.getString("user_id");				
			} 
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ::::");
			
			e.printStackTrace();
		}
		return userIds;
	}

	@Override
	public boolean updateLinkScore(int userId, String total,String action) {
		System.out.println(userId +"  --"+total);
		int queryStatus = 0;
		try(Connection con = (Connection) dataSource.getConnection()) {
			String sql = "update  user set approved_link = ? where user_id = ?";
			if(action.equalsIgnoreCase("scrap1")) {
				sql = "update  user set approved_link = ? where user_id = ?";
			}else if(action.equalsIgnoreCase("scrap2")) {
				sql = "update  user set approved_link_scrap2 = ? where user_id = ?";
			}else if(action.equalsIgnoreCase("scrap3")) {
				sql = "update  user set approved_link_scrap3 = ? where user_id = ?";
			}else if(action.equalsIgnoreCase("company_log")) {
				sql = "update  user set company_link = ? where user_id = ?";
			}
			
			 
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, total);
			ps.setLong(2, userId);
			queryStatus = ps.executeUpdate();
			ps.close();
			con.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("updateLinkScore Status :::"+queryStatus);
		if(queryStatus > 0)
			return true;
		else		
			return false;
	}

	@Override
	public boolean setPendingLink(String action, int userId,int limit) {
		int queryStatus = 0;
		try(Connection con = (Connection) dataSource.getConnection()) {
			PreparedStatement ps = null;
			String sql = "";
			if(action.equalsIgnoreCase("assignDataEntry")){
				sql = "UPDATE master_data_entry_url SET user_id=? 	WHERE master_data_id IN (SELECT master_data_id FROM (SELECT master_data_id FROM master_data_entry_url where user_id=0 and status = 'Active' LIMIT 0, ?  ) tmp )";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, limit);
			}else if(action.equalsIgnoreCase("assignEmailEntry")){
				sql = "UPDATE email_data SET user_id=? 	WHERE email_data_id IN (SELECT email_data_id FROM (SELECT email_data_id FROM email_data where user_id=0 and status = 'Active' LIMIT 0, ?  ) tmp )";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, limit);
			} 
			queryStatus = ps.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("Status :::"+queryStatus);
		if(queryStatus > 0)
			return true;
		else		
			return false;
	}

   

	@Override
	public boolean reActiveMasterURL(String ids,String action) {
		int status = 0; 
		try (Connection con = (Connection) dataSource.getConnection()){
			String sql = "";
			if(action.equalsIgnoreCase("masterScrap")) {
				sql = "update master_url set status='Active' where master_url_id in ("+ids+")";
			}else if(action.equalsIgnoreCase("masterListBuild")) {
				sql = "update master_list_building_url set status='Active' where master_list_url_id in ("+ids+")";	
			}else if(action.equalsIgnoreCase("masterCompany")) {
				sql = "update master_company_url set status='Active' where company_url_id in ("+ids+")";
			}
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.commit();		
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("Status list:::"+status);
		return false;
	}

	@Override
	public List<DataEntryList> exportDataEntry(String startDate, String endDate,String action) {
		// TODO Auto-generated method stub
		List<DataEntryList> profileEmails = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			
			String sql = "";
			if(action.equalsIgnoreCase("emailEntryResult")) {
				sql = "SELECT d.*,u.useremail,dr.name FROM email_entry d,data_entry.user u, data_remarks dr\n" + 
						" where  u.user_id = d.user_id and dr.data_remarks_id = d.remarks  and DATE_FORMAT(d.created_date,'%m/%d/%Y') BETWEEN ? AND ?";
			}else {
				sql = "SELECT d.*,m.url,u.useremail,dr.name FROM data_entry.data_entry d,master_data_entry_url m,data_entry.user u, data_remarks dr\n" + 
						" where d.url_id = m.master_data_id and u.user_id = d.user_id and dr.data_remarks_id = d.remarks  and DATE_FORMAT(d.created_date,'%m/%d/%Y') BETWEEN ? AND ?";
				
			}
			System.out.println("SQL:::"+sql);
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1,startDate);
			ps.setString(2,endDate);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DataEntryList profileEmail = new DataEntryList();
				
				profileEmail.setFirstName(rs.getString("first_name"));;
				profileEmail.setJobTitle(rs.getString("job_title"));;
				profileEmail.setNameSource(rs.getString("name_source"));
				profileEmail.setContactEmail(rs.getString("contact_email"));
				profileEmail.setContactUsFormLink(rs.getString("contact_us_form_link"));
				profileEmail.setUrl_id(rs.getString("url_id"));
				
				if(action.equalsIgnoreCase("emailEntryResult")){
						profileEmail.setUrl("");
						profileEmail.setDataEntryId(rs.getInt("email_entry_id"));
				}else {
					profileEmail.setUrl(rs.getString("url"));
					profileEmail.setDataEntryId(rs.getInt("data_entry_id"));
				}
				
				profileEmail.setContactEmailSource(rs.getString("contact_email_source"));
				profileEmail.setContactPhone(rs.getString("contact_phone"));
				profileEmail.setContactPhoneSource(rs.getString("contact_phone_source"));
				profileEmail.setCompanyName(rs.getString("company_name"));
				profileEmail.setCompanyNameSource(rs.getString("company_name_source"));
				profileEmail.setCompanyWebsite(rs.getString("company_website"));
				profileEmail.setCompanyWebsiteSource(rs.getString("company_website_source"));
				profileEmail.setCompanyEmail(rs.getString("company_email"));
				profileEmail.setCompanyEmailSource(rs.getString("company_email_source"));
				profileEmail.setCompanyPhone(rs.getString("company_phone"));
				profileEmail.setCompanyNameSource(rs.getString("company_phone_source"));
				profileEmail.setPropertyName(rs.getString("property_name"));
				profileEmail.setPropertyWebsite(rs.getString("property_website"));
				profileEmail.setPropertySource(rs.getString("property_source"));
				profileEmail.setAddress(rs.getString("address"));
				profileEmail.setCity(rs.getString("city"));
				profileEmail.setState(rs.getString("state"));
				profileEmail.setZipCode(rs.getString("zip_code"));
				profileEmail.setUser_id(rs.getString("useremail"));
				profileEmail.setRemarks(rs.getString("name"));
				profileEmail.setIpaddress(rs.getString("ipaddress"));
				profileEmail.setCreatedDate(rs.getString("created_date"));
				profileEmails.add(profileEmail);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return profileEmails;
	}	


	
}
