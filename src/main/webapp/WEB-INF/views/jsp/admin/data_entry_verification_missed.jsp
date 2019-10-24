<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Email Entry Verification Missed</title> 
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
 

function setStatus(status,urlId){
	$.ajax({
		type : "POST",
		url : "updateUrlStatus",
		data :{
			status : status,
			urlId : urlId, 
			action : "DataEntry"
		},
		success : function(data){
			
		},
		error : function(e){
			consloe.log("Error ::: "+e);
		}
	});
}


function setOthrerFields(source){
	$("#contactEmailSource").val(source.value); 
	$("#contactPhoneSource").val(source.value);
	$("#companyNameSource").val(source.value);
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
         
         <table class="table table-striped" style="font-size: 13px;">
         	<tr>
         		<th width="3%">#</th>
         		<th width="57%">Url</th> 
         	</tr>   
             <c:forEach items="${urlList }" var="urlList" varStatus="index">         
	         	<tr>
	         		<td>${index.count } </td> 
	         		<td> <a href="#"> ${urlList.url }</a></td> 
	         	</tr>	
	         </c:forEach> 
         </table>
		   
      </div>

      <div id="push"></div>
    </div>

     
     
    <script src="<c:url value="resources/js/bootstrap-dropdown.js"></c:url>"></script>
    

  </body>
</html>