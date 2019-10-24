<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>User Data Entry URL</title> 
    <link rel="icon" href="<c:url value="resources/image/favicone.jpg"></c:url>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->   
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"></c:url>">   
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css"></c:url>">     
    <%-- <link rel="stylesheet" href="<c:url value="resources/css/bootstrap-responsive.css"></c:url>"> --%>
    <link rel="stylesheet" href="<c:url value="resources/css/grid.css"></c:url>">
     
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="<c:url value="resources/js/common.js"></c:url>"></script>
 	
 <style type="text/css">
 textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
 height: 28px !important;         
 } 
 
 .mg_21{ 
 margin-right: 19px;
 margin-top: 13px;  
} 
hr.style-four {
padding: 0;
border: none;
border-top: medium double #333;
color: #333;
text-align: center;
}
hr.style-four:after {
content: "www.formget.com";
display: inline-block;
position: relative;
top: -0.7em;
font-size: 1.5em;
padding: 0 0.25em;
background: white;
}
 
 </style>
 	
<script type="text/javascript">
 
$( document ).ready(function() {
     
	$.ajax({
		type : "POST",
		url  : "getKFBDailyCount",
		data : {
			
		},
		success : function(data){
			//alert(data.userVerificationApproved);
		 
			$("#userVerificationApproved").html(data.userVerificationApproved);
			$("#userVerificationContacts").html(data.userVerificationContacts);
			$("#userVerificationEmails").html(data.userVerificationEmails);
			
		},error : function(error){
			console.log("getKFBDailyCount"+error);
		}
	}); 
	
}); 

function setStatus(status,urlId){
	
	if(confirm("Are you sure you want to submit?")){	
		$.ajax({
			type : "POST",
			url : "updateUrlStatus",
			data :{
				status : status,
				urlId : urlId, 
				action : "DataEntry"
			},
			success : function(data){
				location.reload();
			},
			error : function(e){
				consloe.log("Error ::: "+e);
			}
		});
	} 	
}


function setOthrerFields(source){
	$("#contactEmailSource").val(source.value); 
	$("#contactPhoneSource").val(source.value);
	$("#companyNameSource").val(source.value);
}
function checkValidation(){
	if($("#remarks").val()==""){
		alert("Please select remarks");
		return false;   
	}
}

function openWindow(url){
	window.open(url.value);   
}

</script>	
</head>

<body>
    <div id="wrap">
      <!-- Fixed navbar Start-->
      <%@include file="include/header.jsp" %>
      <!-- Fixed navbar End -->
       

      <!-- Begin page content --> 
      <div class="container">
        <font style="text-align: left;" size="3" ><strong> Pending </strong> ${userVerificationActive }</font>  &nbsp;&nbsp; 
        <span style="float: right;"><font style="text-align: left;" size="3" >Total Pending Link in System : </font><font size="5" color="black"> ${getTotalActiveLink }</font></span>
        <font style="text-align: left;" size="3" ><strong> Count </strong> (<a href="<%=request.getContextPath() %>/dataEntryVerificationLog"> <span id="userVerificationApproved">0</span> </a>) </font> &nbsp;&nbsp; 
        <font style="text-align: left;" size="3" ><strong> Contacts </strong> (<span id="userVerificationContacts">0</span>)</font> &nbsp;&nbsp; 
        <font style="text-align: left;" size="3" ><strong> Email </strong> (<span id="userVerificationEmails">0</span>)</font> &nbsp;&nbsp; &nbsp;&nbsp;   
      
		<c:if test="${userVerificationActive == 0}">
        	<input type="button" name="getMoreLinks" id="getMoreLinks" class="btn btn-primary" onclick="getMoreLinks('assignDataEntry')" value="Get More 1 Links">
        </c:if><br />   
                 
         <table class="table table-striped" >
         	<tr>
         		<th width="3%">#</th>
         		<th width="57%">Url</th>         		
         		<th width="20%">Status</th>
         	</tr>   
             <c:forEach items="${urlList }" var="urlList" varStatus="index">         
	         	<tr>
	         		<td>${index.count } </td> 
	         		<td>    
	         			<a href="#" onclick="window.open('${urlList.url }','popUpWindow','left=50,top=50,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');" > ${urlList.url }</a> 
	         		</td> 
	         		<td><a onclick="setStatus('Done',${urlList.urlId})" href="#">Done</a></td>
	         	</tr>	
	         </c:forEach>  
         </table>
        
        
     <c:if test="${userVerificationActive gt 0}"> 
    <div class="col-md-4">
      &nbsp;
    </div> 
    <div class="col-md-4 text-center">
      Source
    </div> 
    <div class="col-md-4">
      &nbsp; 
    </div>
    
     <br /> 
     <form action="insertDataEntry" method="post">
     
    <div class="col-md-2"> 
     	<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Full Name</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Contact Email</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Contact Phone</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Company Name</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Compnay Website</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Company Email</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Company Phone</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Property Name</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Contact US Form Link</label>
		</div>
		<div class="form-group row text-right mg_21">  
		  <label for="colFormLabelSm" class="col-form-label col-form-label-sm">Remarks</label>
		</div>
    </div> 
     
	<div class="col-md-3"> 
	   <div class="form-group row">
		  <input type="text" class="form-control form-control-sm" id="fullName" name="firstName" placeholder="Full Name">
	  </div> 
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="contactEmail" name="contactEmail" placeholder="Contact Email"> 
	  </div> 
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="contactPhone" name="contactPhone" placeholder="Contact Phone"> 
	  </div>
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="companyName" name="companyName" placeholder="Company Name"> 
	  </div>
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="companyWebsite" name="companyWebsite" placeholder="Company Website"> 
	  </div>
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="companyEmail" name="companyEmail" placeholder="Company Email"> 
	  </div>
	  <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="companyPhone" name="companyPhone" placeholder="Company Phone"> 
	  </div>
	  <div class="form-group row">   
	      <input type="text" class="form-control form-control-sm" id="propertyName" name="propertyName" placeholder="Property Name"> 
	  </div>
	  <div class="form-group row">   
	      <input type="text" class="form-control form-control-sm" id="contactUsFormLink" name="contactUsFormLink" placeholder="Contact US Form Link"> 
	  </div>
	   
	  <div class="form-group row">
		      <select id="remarks" name="remarks" class="form-control">
		        <option value="" selected>Select Remarks</option>  
		        <option value="1">Site Under Construction</option>
		        <option value="2">Site Not Live</option>
		        <option value="3">Invalid_Owner/PM</option>
				<option value="4">Zip Code Not Match</option>
				<option value="5">Email Not Found</option>
				<option value="6">Other Country</option>
				<option value="7">Site Redirect</option>
				<option value="8">Address Not Found</option>
				<option value="9">Contact Not Found </option> 
				<option value="10">Contact Found </option>
				<option value="11">Email Found </option>
				<option value="12">Contact & Email Found </option>
				<option value="13">Contact & Email Not Found </option>
				<option value="14">Duplicate Site </option>
		      </select> 
		    </div> 
	    
	</div>
	  
	<div class="col-md-3">
		<div class="form-group row">
		<!-- <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Job Title</label> -->
	      <input type="text" class="form-control form-control-sm" id="nameSource" onchange="setOthrerFields(this)" name="nameSource" placeholder="Source">
	   </div> 
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="contactEmailSource" name="contactEmailSource" placeholder="Contact Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="contactPhoneSource" name="contactPhoneSource" placeholder="Contact Phone Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="companyNameSource" name="companyNameSource" placeholder="Company Name Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="companyWebsiteSource" name="companyWebsiteSource" placeholder="Company Website Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="companyEmailSource" name="companyEmailSource" placeholder="Company Email Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="companyPhoneSource" name="companyPhoneSource" placeholder="Company Phone Source">
	   </div>
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="propertySource" name="propertySource" placeholder="Property Name Source">
	   </div> 	   
	</div>  
	<div class="col-md-3">
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="jobTitle" name="jobTitle" placeholder="Job Title">
	   </div>
		<!-- <hr class="style-four"> -->
		<div class="form-group row"> 
			<strong>Address</strong>
		</div>   	   
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="address" name="address" placeholder="Address">
	   </div>  
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="city" name="city" placeholder="City">
	   </div>  
	   <div class="form-group row">
	      <input type="text" class="form-control form-control-sm" id="state" name="state" placeholder="State">
	   </div>  
	   <div class="form-group row"> 
	      <input type="text" class="form-control form-control-sm" id="zipCode" name="zipCode" placeholder="Zip Code">
	   </div>    
	   <input type="submit" class="btn btn-primary" onclick="return checkValidation();" value="Save" />   
	</div>   
         </form> 
         
         
         <table class="table table-striped" >
         	<tr>
         		<th width="3%">#</th>
         		<th width="32%">Full Name</th>         		
         		<th width="25%">Job Title</th>
         		<th width="25%">Contact Email</th>
         		<th width="35%">Contact US Form</th>         		
         	</tr>     
             <c:forEach items="${dataEntryList }" var="dataEntryList" varStatus="index">         
	         	<tr>
	         		<td>${index.count } </td> 
	         		<td>${dataEntryList.firstName }</td> 
	         		<td>${dataEntryList.jobTitle }</td> 
	         		<td>${dataEntryList.contactEmail }</td>
	         		<td>${dataEntryList.contactUsFormLink }</td>         		
	         	</tr>	
	         </c:forEach>   
         </table>
         
         </c:if>
         
         
         
      <div id="push"></div>
    </div>

     
     
    <script src="<c:url value="resources/js/bootstrap-dropdown.js"></c:url>"></script>
    

  </body>
</html>