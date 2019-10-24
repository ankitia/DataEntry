<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Dashboard</title> 
    <link rel="icon" href="<c:url value="resources/image/favicone.jpg"></c:url>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->   
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"></c:url>"> 
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css"></c:url>">     
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap-responsive.css"></c:url>">
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript">


function setTotal(total,action){
	
	//if(confirm("Are you sure you want to confirm?")){
		 $.ajax({
			 url : "updateLinkScore",
			 type : "POST",
			 data :{
				 "total" : total,
				 "action" :action
			 },
			 success: function(data){
				 if(data=="true"){
					 //alert("Score updated successfully");					 
					 if(action=="scrap1"){
						 $("#totalApproved").hide();
					 }else{
						 $("#totalApproved2").hide();	 
					 } 
					 
					 					 
				 }else{
				 	 
				 }
				 
				 
			 },
			 error : function(e) {
				console.log("Error setTotal ::"+e);
			}
			 
			 
		 })
		 
	//}
	
}
</script> 	
	
  </head>

  <body>

    <div id="wrap">
      <!-- Fixed navbar Start-->
      <%@include file="include/header.jsp" %>
      <!-- Fixed navbar End -->
       

      <!-- Begin page content -->  
      <div class="container text-center">
         
         <font color="red" size="4" style="float: right;"> Welcome, <%=session.getAttribute("userName") %> </font>
         
         <table class="table table-striped" style="    font-size: 16px;">
         	<tr>
         		<th width="25%">Link</th>
         		<th style="text-align: center;" width="10%">Pending</th>
         		<th style="text-align: center;" width="10%">Approved</th>
         		<th style="text-align: center;" width="10%">Missed Link</th>
         		<th style="text-align: center;" width="10%">Total</th>
         		<th style="text-align: center;" width="10%">In Last Hour</th>
         		<th>In Last Eight Hour</th>
         	</tr>     	 
			<tr>
				<td>User Verification</td>
				<td style="text-align: center;" ><a href="<%=request.getContextPath() %>/userUrl"> ${userVerificationActive }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/userVerificationLog"> ${userVerificationApproved }</a></td>
				<td style="text-align: center;"> <a href="<%=request.getContextPath() %>/userVerificationMissed"><!--${userVerificationMissed }-->  ${userVerificationAll -(userVerificationActive +userVerificationApproved)}</a></td>
				<td style="text-align: center;">${userVerificationAll } </td> 
				<td style="text-align: center;">${userLastHour }</td>
				<td  > ${userTotalHour}</td>
			</tr>
			<tr>
				<td>Full Profile Details</td>
				<!-- <td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td> -->
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/userProfile">${userProfileActive }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/userProfileLog">${userProfileApproved }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/userProfileMissed">${userProfileAll  -(userProfileActive+userProfileApproved) }</a></td> 
				<td style="text-align: center;">${userProfileAll }</td>
				<td style="text-align: center;">${userProfileLastHour }</td>
				<td>${userProfileTotalHour }</td>
			</tr>
			<tr>
				<td>List Building</td> 
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/listBuildingUrl">${listBuildVerificationActive }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/listBuildVerificationLog">${listBuildVerificationApproved }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/listBuildVerificationMissed">${listBuildVerificationAll  -(listBuildVerificationActive+listBuildVerificationApproved) }</a></td> 
				<td style="text-align: center;">${listBuildVerificationAll }</td>
				<td style="text-align: center;">${listBuildLastHour }</td>
				<td>${listBuildTotalHour }</td> 
			</tr>               
			<tr>
				<td>Company Details</td>
				<td style="text-align: center;" ><a href="<%=request.getContextPath() %>/companyUrl"> ${companyVerificationActive }</a></td>
				<td style="text-align: center;"><a href="<%=request.getContextPath() %>/companyVerificationLog"> ${companyVerificationApproved }</a></td>
				<td style="text-align: center;"> <a href="<%=request.getContextPath() %>/companyVerificationMissed">${companyVerificationAll -(companyVerificationActive +companyVerificationApproved)}</a></td>
				<td style="text-align: center;">${companyVerificationAll }</td>
				<td style="text-align: center;">${companyLastHour }</td>
				<td  > ${companyTotalHour}</td>
			</tr>                
         </table>
         
         
         
		 	<%--  
		 	<c:if test="${userDetail.approvedLink eq ''}">
		 		test
		 	</c:if>
		 	--%>  
		 	 <c:set var="total" value="0"></c:set> 
		   <%--  <% if(session.getAttribute("approvedLink")==null || session.getAttribute("approvedLink").toString().trim()=="" || session.getAttribute("approvedLink").toString().length() == 0){  %>
				   <h3 id="totalApproved" class="text-left">User Verification till(26-Sep-2019) :  ${userVerificationApprovedLog } <a href="#" onclick="return setTotal(${userVerificationApprovedLog},'scrap1')" class="btn btn-primary">Please confirm </a> </h3>
			<% }else{
				%>
				<h3  class="text-left">User Verification till(26-Sep-2019) :  ${userVerificationApprovedLog } <a href="#" class="btn btn-primary">Approved </a> </h3>
				<c:set var="total" value="${userVerificationApprovedLog }"></c:set>
			<% }%>
			
				<% if(session.getAttribute("approvedLink2")==null || session.getAttribute("approvedLink2") == "" || session.getAttribute("approvedLink2").toString().length() == 0){  %>
				   <h3 id="totalApproved2" class="text-left">Full Profile Details  :  ${userVerificationApprovedLog2 } <a href="#" onclick="return setTotal(${userVerificationApprovedLog2},'scrap2')" class="btn btn-primary">Please confirm </a> </h3>
			<% }else{
				%> 
				<h3  class="text-left">Full Profile Details  :  ${userVerificationApprovedLog2 } <a href="#" class="btn btn-primary">Approved </a> </h3>
				<c:set var="total" value="${ total + userVerificationApprovedLog2 }"></c:set>
			<% }%> --%> 
		   
		   	<%--<% if(session.getAttribute("approvedLink3")==null || session.getAttribute("approvedLink3")=="" || session.getAttribute("approvedLink3").toString().length() == 0){  %>
				   <h3 id="totalApproved2" class="text-left">User Verification from (19-Jan-2019) to (27-Jan-2019) :  ${userVerificationApprovedLog3 } <a href="#" onclick="return setTotal(${userVerificationApprovedLog3},'scrap3')" class="btn btn-primary">Please confirm </a> </h3>
			<% }else{
				%>
				<h3  class="text-left">User Verification from (19-Jan-2019) to (27-Jan-2019) :  ${userVerificationApprovedLog3 } <a href="#" class="btn btn-primary">Approved </a> </h3>
				<c:set var="total" value="${ total + userVerificationApprovedLog3 }"></c:set>
			<% }%>--%>
			
			   	<% if(session.getAttribute("companyLink")==null || session.getAttribute("companyLink")=="" || session.getAttribute("companyLink").toString().length() == 0){  %>
				   <h3 id="totalApproved2" class="text-left">Company Details from (01-Sep-2019) to (26-Sep-2019) :  ${companyVerification } <a href="#" onclick="return setTotal(${companyVerification},'company_log')" class="btn btn-primary">Please confirm </a> </h3>
			<% }else{
				%>
				<h3  class="text-left">Company Details till(26-Sep-2019) :  ${companyVerification } <a href="#" class="btn btn-primary">Approved </a> </h3>
				<c:set var="total" value="${ total + companyVerification }"></c:set>
			<% }%>
		   
		   <h3  class="text-left"> Final total : ${total }</h3>   
		    
		
      </div>
      <div id="push"></div>
    </div>

    <script src="<c:url value="resources/js/bootstrap-dropdown.js"></c:url>"></script>
  </body>
</html>