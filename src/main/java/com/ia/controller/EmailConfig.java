package com.ia.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.ia.Dao.EmailEntryDAO;
import com.ia.modal.EmailData;

public class EmailConfig {

	public static void check(String host, String storeType, String user,
		      String password,EmailEntryDAO emailDAO,int emailId) 
		   {
			   
			   System.out.println(host +"  "+user+"  "+password);
			   
		      try {

		      //create properties field
		      Properties properties = new Properties();

		      properties.put("mail.pop3.host", host);
		      //properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.port", "993");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      //Store store = emailSession.getStore("pop3s");
		      Store store = emailSession.getStore("imaps");

		      //store.connect(host, user, password);
		      store.connect("imap.gmail.com", user, password);
		      

		      System.out.println("Inbox unreadmessaage count ::"+ store.getFolder("INBOX").getUnreadMessageCount());
		      
		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      //emailFolder.open(Folder.READ_ONLY);
		      emailFolder.open(Folder.READ_WRITE);
		      
		      
		      //System.out.println("getUnreadMessageCount-->"+emailFolder.getUnreadMessageCount());
		      
		      // retrieve the messages from the folder in an array and print it
		      Message[] messages = emailFolder.getMessages();
		      System.out.println("messages.length---" + messages.length);
		      System.out.println("messages.folder total---" + emailFolder.getMessageCount() +"---"+emailFolder.getNewMessageCount()+"---"+emailFolder.getUnreadMessageCount());
		      	
		      for (int i = 1051, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         System.out.println("Receive Date: " + message.getReceivedDate());
		         
		         if(message.getReceivedDate().after(new SimpleDateFormat("MM/dd/yyyy").parse("10/05/2019"))) {
			    	  /*System.out.println(i+ "--"+toString(message));*/
		         
		      /*   System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());*/
		        /* System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("To: " + message.getAllRecipients()[0]);
		        */ 
		         /*System.out.println("Send Date: " + message.getSentDate());
		         System.out.println("Text: " + message.getContent().toString());
		         System.out.println("length-------------"+message.getAllRecipients().length);*/
		         
		         EmailData emailData = new EmailData(); 
		         emailData.setContent(toString(message));
		         
		         if(message.getFrom()!=null)
		        	 emailData.setEmailFrom(message.getFrom()[0]+"");
		         emailData.setEmailNumber((i + 1));
		         
		         if(message.getAllRecipients()!=null)
		        	 emailData.setEmailTo(message.getAllRecipients()[0]+"");
		         
		         emailData.setMasterEmailId(emailId);
		         emailData.setReceivedDate(message.getReceivedDate()+"");
		         emailData.setSendDate(message.getSentDate()+"");
		         emailData.setSubject(message.getSubject());
		         emailData.setContactList(searchContactDetails(message.getContent().toString()));
		         emailData.setEmailList(searchEmailDetails(message.getContent().toString()));
		         
		         
		        	System.out.println("True");
		        	 emailDAO.insertEmailData(emailData); 
		         }
		         
		      }

		      //close the store and folder objects
		      emailFolder.close(false);
		      store.close();

		      } catch (NoSuchProviderException e) {
		         e.printStackTrace();
		      } catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
		   
		   public static String searchContactDetails(String line) {
			   String contactList = "";
			   Pattern p = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
			   Matcher m = p.matcher(line); 
	           while (m.find()) { 
	        	    System.out.println(m.group());
	           		contactList +=  m.group()+",";
	           } 
			   return contactList ;
		   }
		   
		   public static String searchEmailDetails(String line) {
			   String emailList = "";
			   Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
			   Matcher m = p.matcher(line); 
	           while (m.find()) { 
	        	    System.out.println(m.group());
	           		emailList +=  m.group()+",";
	           } 
			   return emailList ;
		   }
		   //regexp = "\\b((Chicago,?|Houston,?|Philadelphia,?|Phoenix,?|Alabama,?|Alaska,?|Arizona,?|Arkansas,?|California,?|Colorado,?|Connecticut,?|Delaware,?|Florida,?|Georgia,?|Hawaii,?|Idaho,?|Illinois,?|Indiana,?|Iowa,?|Kansas,?|Kentucky,?|Louisiana,?|Maine,?|Maryland,?|Massachusetts,?|Michigan,?|Minnesota,?|Mississippi,?|Missouri,?|Montana,?|North Carolina,?|North Dakota,?|Nebraska,?|Nevada,?|New Hampshire,?|New Jersey,?|New Mexico,?|New York,?|Ohio,?|Oklahoma,?|Oregon,?|Pennsylvania,?|Rhode Island,?|South Carolina,?|South Dakota,?|Tennessee,?|Texas,?|Utah,?|Vermont,?|Virginia,?|Washington,?|West Virginia,?|Wisconsin,?|Wyoming,?|American Samoa,?|D\\.C\\.,?|Guam,?|Puerto Rico,?|AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NC|ND|NE|NV|NH|NJ|NM|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY|AS|DC|GU|MP|PR|VI)\\s+\\d{5}(-\\d{4})?)\\b"

		   public static void main(String[] args) {

		      String host = "pop.gmail.com";// change accordingly
		      String mailStoreType = "pop3";
		      String username = "johngibson899@gmail.com";// change accordingly
		      String password = "april@2019";// change accordingly

		      //check(host, mailStoreType, username, password);

		   }
		   
		   public static String toString(Message message) throws IOException, MessagingException, javax.mail.MessagingException {
			    Object content = message.getContent();
			    if (content instanceof MimeMultipart) {
			        MimeMultipart multipart = (MimeMultipart) content;
			        if (multipart.getCount() > 0) {
			            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(0);
			            content = part.getContent();
			        }
			    }
			    
			    //System.out.println(content.toString());
			    
			    if (content != null) {
			        return content.toString();
			    }
			    
			    
			    return null;
			}
	
}
