 

function checkValidUser(){
	
	if($("#userEmail").val()==""){
		alert("Please enter user name");
		return false;
	}else if($("#userPassword").val()==""){
		alert("Please enter password");
		return false;
	}
	
	$.ajax({
		
		type : "POST",
		url  : "checkValidUser",
		data : {
			userEmail : $("#userEmail").val(),
			userPassword : $("#userPassword").val(),
		},
		success : function(data){
			
			
			alert(data);
			location.reload();
			
			
		},
		error : function(e){
			console.log("Error  ==>"+ e);
		}
		
		
		
	});
	
	
	
}

function signUp(){
$.ajax({
		
		type : "POST",
		url  : "signup", 
		data : {
			fname : $("#fname").val(),
			lname : $("#lname").val(),
			pass : $("#pass").val(),
			email : $("#email").val(),
			
		},
		success : function(data){
			
			alert(data);
			location.reload();
			
			
		},
		error : function(e){
			console.log("Error  ==>"+ e);
		}
		
		
		
	});
}