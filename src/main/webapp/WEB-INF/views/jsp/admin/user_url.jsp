<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>User URL</title> 
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
	<script src="<c:url value="resources/js/common.js"></c:url>"></script>
 	
<script type="text/javascript">

function setStatus(status,urlId){
	
	$.ajax({
		type : "POST",
		url : "updateUrlStatus",
		data :{
			status : status,
			urlId : urlId,
			action : "scrap"
		},
		success : function(data){
			
		},
		error : function(e){
			consloe.log("Error ::: "+e);
		}
		
		
	});
	
}

</script>	
	 
	 
    
  </head>

  <body>

    <div id="wrap">
      <!-- Fixed navbar Start-->
      <%@include file="include/header.jsp" %>
      <!-- Fixed navbar End -->
       

      <!-- Begin page content --> 
      <div class="container" style="margin-top: 10px;">
         
         <font style="text-align: left;" size="3" ><strong> Pending </strong> ${userVerificationActive }</font> <br />
         <span style="float: right;"><font style="text-align: left;" size="3" >Total Pending Link in System : </font><font size="5" color="black"> ${getTotalActiveLink }</font></span>
        <font style="text-align: left;" size="3" ><strong> Approved </strong> (<a href="<%=request.getContextPath() %>/userVerificationLog">${userVerificationApproved }</a>) </font> <br />
        <font style="text-align: left;" size="3" ><strong> Missed </strong> (<a href="<%=request.getContextPath() %>/userVerificationMissed">${userVerificationAll -(userVerificationActive +userVerificationApproved)}</a>)</font> <br />

		<c:if test="${userVerificationActive == 0}">
        	<input type="button" name="getMoreLinks" id="getMoreLinks" class="btn btn-primary" onclick="getMoreLinks('scrap')" value="Get More 50 Links">
        </c:if>
                 
         <div style="text-align: right;">  
         		
         		<font  size="3" > In Last Hour Clicked (<font color="green" size="4"> ${userLastHour } </font>) </font>
         </div>
         <div style="margin-top: 10px;text-align: right;margin-bottom: 10px;"> 
        	 
         	 <font  size="3" > In Last Eight Hour Clicked (<font color="green" size="4"> ${userTotalHour } </font>) </font> 
         </div>
         
         
         <table class="table table-striped" >
         	<tr>
         		<th width="3%">#</th>
         		<th width="57%">Url</th>         		
         	</tr>   
         	  	 
             <c:forEach items="${urlList }" var="urlList" varStatus="index">         
	         	<tr>
	         		<td>${index.count } </td> 
	         		<td>
	         			<a href="${urlList.url }&id=${urlList.userId}&urlId=${urlList.urlId}"  onclick="setStatus('Done',${urlList.urlId})" target="_blank"> Link ${urlList.urlId }</a> 
	         		</td>
	         	</tr>	
	         </c:forEach>  
         </table>
         
      </div>

      <div id="push"></div>
    </div>

     
     
    <script src="<c:url value="resources/js/bootstrap-dropdown.js"></c:url>"></script>
    

  </body>
</html>