package com.ia.modal;

public class EmailData {
	private int id;
	private int emailNumber;
	private String subject;
	private String emailFrom;
	private String emailTo;
	private String receivedDate;
	private String sendDate;
	private String content;
	private int masterEmailId;
	private String emailList;
	private String contactList;
	
	
	public String getContactList() {
		return contactList;
	}
	public void setContactList(String contactList) {
		this.contactList = contactList;
	}
	public String getEmailList() {
		return emailList;
	}
	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmailNumber() {
		return emailNumber;
	}
	public void setEmailNumber(int emailNumber) {
		this.emailNumber = emailNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMasterEmailId() {
		return masterEmailId;
	}
	public void setMasterEmailId(int masterEmailId) {
		this.masterEmailId = masterEmailId;
	}
}