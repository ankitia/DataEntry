package com.ia.IMPL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.Dao.EmailEntryDAO;
import com.ia.modal.DataEntry;
import com.ia.modal.EmailData;
import com.ia.modal.MasterDataEntryURL;
import com.ia.modal.MasterEmail;
import com.mysql.jdbc.Statement;


@Component("emailEntryDAO")
public class EmailEntryImpl implements EmailEntryDAO {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.IMPL.email", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	@Override
	public int insertEmailData(EmailData emailData) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertemailData"),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, emailData.getEmailNumber());
			ps.setString(2,emailData.getSubject());
			ps.setString(3,emailData.getEmailFrom());
			ps.setString(4,emailData.getEmailTo());
			ps.setString(5,emailData.getReceivedDate());
			ps.setString(6,emailData.getSendDate());
			ps.setString(7,emailData.getContent());
			ps.setInt(8,emailData.getMasterEmailId());
			ps.setString(9,emailData.getContactList());
			ps.setString(10,emailData.getEmailList());
			status = ps.executeUpdate();
			 ResultSet keys = ps.getGeneratedKeys();
			 while (keys.next()) {
				 status = keys.getInt(1);
			    }
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return status;
	}
 
	@Override
	public ArrayList<MasterEmail> getEmailData(int status) {
		ArrayList<MasterEmail> addresses = new ArrayList<>();
		try(Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement(userDao.getString("getEmailData"));
			ResultSet rs = statement.executeQuery();) {
			while (rs.next()) {
			MasterEmail address = new MasterEmail();
			address.setId(rs.getInt("master_email_id"));
			address.setEmailId(rs.getString("email_id"));
			address.setPassword(rs.getString("password"));
			address.setHost(rs.getString("host"));
			addresses.add(address);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return addresses;
	}
	
	
	@Override
	public int insertEmailEntry(DataEntry dataEntry) {
		// TODO Auto-generated method stub
		int status = 0; 
		try (Connection con = (Connection) dataSource.getConnection()){
			String sql = "insert into email_entry(first_name,job_title,name_source,contact_email,contact_email_source,contact_phone,contact_phone_source,company_name,company_name_source,company_website,company_website_source,company_email,company_email_source,company_phone,company_phone_source,property_name,property_website,property_source,contact_us_form_link,chk_contact_found,chk_contact_us_form,chk_site_not_valid,chk_site_down,ipaddress,user_id,url_id,remarks,address,city,state,zip_code) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dataEntry.getFirstName());
			ps.setString(2, dataEntry.getJobTitle());
			ps.setString(3, dataEntry.getNameSource());
			ps.setString(4, dataEntry.getContactEmail());
			ps.setString(5, dataEntry.getContactEmailSource());
			ps.setString(6, dataEntry.getContactPhone());
			ps.setString(7, dataEntry.getContactPhoneSource());
			ps.setString(8, dataEntry.getCompanyName());
			ps.setString(9, dataEntry.getCompanyNameSource());
			ps.setString(10, dataEntry.getCompanyWebsite());
			ps.setString(11, dataEntry.getCompanyWebsiteSource());
			ps.setString(12, dataEntry.getCompanyEmail());
			ps.setString(13, dataEntry.getCompanyEmailSource());
			ps.setString(14, dataEntry.getCompanyPhone());
			ps.setString(15, dataEntry.getCompanyPhoneSource());
			ps.setString(16, dataEntry.getPropertyName());
			ps.setString(17, dataEntry.getCompanyWebsite());
			ps.setString(18, dataEntry.getPropertySource());
			ps.setString(19, dataEntry.getContactUsFormLink());
			ps.setString(20, dataEntry.getChkContactFound());
			ps.setString(21, dataEntry.getChkContactUsForm());
			ps.setString(22, dataEntry.getChkSiteNotValid());
			ps.setString(23, dataEntry.getChkSiteDown());
			ps.setString(24, dataEntry.getIpaddress());
			ps.setString(25, dataEntry.getUser_id());
			ps.setString(26, dataEntry.getUrl_id());
			ps.setString(27, dataEntry.getRemarks());
			ps.setString(28, dataEntry.getAddress());
			ps.setString(29, dataEntry.getCity());
			ps.setString(30, dataEntry.getState());
			ps.setString(31, dataEntry.getZipCode());
			status = ps.executeUpdate();
			con.commit();		
			 try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	status = generatedKeys.getInt(1);
		            } else {
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }catch (Exception e) {
		        	e.printStackTrace();
				}
			 con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Status insertEmailEntry:::"+status);
		return status;
	}
	
	@Override
	public List<MasterDataEntryURL> getEmailEntryUrlList(int userId,String action) {
		// TODO Auto-generated method stub
		List<MasterDataEntryURL> data = new ArrayList<>();
		try(Connection con = (Connection) dataSource.getConnection();) {
			ResultSet rs = null;
			PreparedStatement ps = null;
			String sql = "";
			if(action.equalsIgnoreCase("active")) {
				sql = "select * from email_data where status = 'Active' and user_id = ?";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1,userId);
					
			}else if(action.equalsIgnoreCase("all")){
				sql = "select * from email_data where user_id = ?";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1,userId);
			}else if(action.equalsIgnoreCase("missed")) {
				//sql = "select m.* from master_zillow_url m where  m.status='Done' and  m.user_id = ? and m.master_zillow_url_id not in (select group_concat(url_id) from scrap where user_id=?)  limit 0,20;";
				sql = "select m.* from email_data m where  m.status='Done' and  m.user_id = ? and m.email_data_id not in (select url_id from profile_email_data where user_id=?);";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1,userId);
				ps.setInt(2,userId);	
			}else if(action.equalsIgnoreCase("display")) {
				sql = "select * from email_data where status = 'Active' and user_id = ? limit 0,1";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1,userId);				
			}
			rs = ps.executeQuery();
			//System.out.println("sql-->"+sql);
			while (rs.next()) {
				MasterDataEntryURL masterURL = new MasterDataEntryURL();
				masterURL.setUrlId(Long.parseLong(rs.getString("email_data_id")));
				masterURL.setUrl((rs.getString("content")));
				masterURL.setFromEmail((rs.getString("email_from")));
				masterURL.setUserId(rs.getString("user_id").trim()!=""?Integer.parseInt(rs.getString("user_id")): 0);
				data.add(masterURL);
			}
			con.close();			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return data;
		
	}
	
	@Override
	public List<DataEntry> getEmailEntry(int userId) {
		// TODO Auto-generated method stub
			List<DataEntry> profileEmails = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			//String sql = "select * from data_entry where user_id = ? order by email_entry_id desc limit 0,10";
			String sql = "select * from email_entry de,email_data md where md.email_data_id = de.url_id and de.user_id = ? order by de.email_entry_id desc  limit 0,10";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DataEntry profileEmail = new DataEntry();
				profileEmail.setDataEntryId(rs.getInt("email_entry_id"));
				profileEmail.setFirstName(rs.getString("first_name"));;
				profileEmail.setJobTitle(rs.getString("job_title"));;
				profileEmail.setNameSource(rs.getString("name_source"));
				profileEmail.setContactEmail(rs.getString("contact_email"));
				profileEmail.setUrl_id(rs.getString("url_id"));
				profileEmail.setUrl(rs.getString("url"));
				profileEmails.add(profileEmail);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return profileEmails;
	}
	
	@Override
	public List<DataEntry> getEmailEntryByUrlId(int urlId) {
		// TODO Auto-generated method stub
			List<DataEntry> profileEmails = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			//String sql = "select * from data_entry where url_id = ? order by email_entry_id desc";
			String sql = "select * from email_entry de,email_data md where md.email_data_id = de.url_id and de.url_id = ? order by de.email_entry_id desc";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,urlId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DataEntry profileEmail = new DataEntry();
				profileEmail.setDataEntryId(rs.getInt("email_entry_id"));
				profileEmail.setFirstName(rs.getString("first_name"));;
				profileEmail.setJobTitle(rs.getString("job_title"));;
				profileEmail.setNameSource(rs.getString("name_source"));
				profileEmail.setContactEmail(rs.getString("contact_email"));
				profileEmail.setContactUsFormLink(rs.getString("contact_us_form_link"));
				profileEmail.setUrl_id(rs.getString("url_id"));
				profileEmail.setUrl("");
				profileEmails.add(profileEmail);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return profileEmails;
	}

	@Override
	public EmailData getEmailDataByUrlId(int urlId) {
		// TODO Auto-generated method stub
		List<EmailData> profileEmails = new ArrayList<>();
		try {
			Connection con = (Connection) dataSource.getConnection();
			//String sql = "select * from data_entry where url_id = ? order by email_entry_id desc";
			String sql = "select * from email_data where email_data_id =?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,urlId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				EmailData profileEmail = new EmailData();
				profileEmail.setContent(rs.getString("content"));
				profileEmail.setId(rs.getInt("email_data_id"));;
				profileEmails.add(profileEmail);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return profileEmails.get(0);
	}

	 
	
}
