package com.ia.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeMultipart;


public class SendSMS {

	
	public static void main(String[] arg) {
	 System.out.println(sendSms());
	}
	
	public static String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "ZAWzDUUzVr8-Icoyzxa9gOFH4S4U5RlC3kRWX7xDex";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "VK-040060";
			String numbers = "&numbers=" + "9033908637";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("Success SMS ");
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	
	public static String toString(String message) throws IOException, MessagingException, javax.mail.MessagingException {
	    Object content = message;
	    if (content instanceof MimeMultipart) {
	        MimeMultipart multipart = (MimeMultipart) content;
	        if (multipart.getCount() > 0) {
	            MimeBodyPart part = multipart.getBodyPart(0);
	            content = part.getContent();
	        }
	    }
	    
	    System.out.println(content.toString());
	    
	    if (content != null) {
	        return content.toString();
	    }
	    
	    
	    return null;
	}
	
}
