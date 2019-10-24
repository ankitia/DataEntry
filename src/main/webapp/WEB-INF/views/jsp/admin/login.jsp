<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">   
<head>
<title>Login</title>
  
	<link rel="icon" href="<c:url value="resources/image/favicone.jpg"></c:url>" />
	
<link rel="stylesheet" href="<c:url value="resources/css/style.css"></c:url>">
<%-- <link rel="stylesheet" href="<c:url value="resources/bootstrap/css/bootstrap.min.css"></c:url>"> --%> 
<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"></c:url>"> 
<link rel="stylesheet" href="<c:url value="resources/css/font-awesome.min.css"></c:url>">
 
<script src="<c:url value="resources/js/jquery.min.js"></c:url>"></script>
<script src="<c:url value="resources/js/jquery.validate.min.js"></c:url>"></script>
<script src="<c:url value="resources/bootstrap/js/bootstrap.min.js"></c:url>"></script>
<script src="<c:url value="resources/bootstrap/js/ValidationFormScript.js"></c:url>"></script>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<style type="text/css">
.wrapper{
overflow-y:hidden; 
}

</style>

</head>
<body class="wrapper">
 <!-- id="form1" -->
 <form action="checkUser" method="post" class="form-horizontal" >
<div class="form-signin ">
  
	  <fieldset>	    
	    <!-- Form Name -->     
	    <h3 class="brand-home text-center mg0"><strong> Data Collection </strong></h3>
	    <hr>
	    
	    <!-- Text input-->
	    <div class="form-group">
	      <label class="col-md-3 control-label" for="Email">E-Mail</label>
	      <div class="col-md-9">
	        <div class="input-group"> <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
	          <input id="Email" required name="Email" type="text" placeholder="Enter Your Email" class="form-control input-md">
	        </div>
	      </div>
	    </div>
	    
	    <!-- Password input-->
	    <div class="form-group">
	      <label class="col-md-3 control-label" for="Password">Password</label>
	      <div class="col-md-9">
 	        <div class="input-group"> <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
	          <input id="password" required name="password" type="password" placeholder="Enter Your Password" class="form-control input-md">
	        </div>
	      </div>
	    </div>
	   
	   <c:if test="${fn:length(message) > 0 }">
		    <div class="form-group">
		      <div class="col-md-12 text-center">   
		        <font color="red" size="3" >${message}</font> <br>
		       </div>
		    </div>
	    </c:if>   
	    <!-- Button -->
	    <div class="form-group">
	      <label class="col-md-4 control-label" ></label>
	      <div class="col-md-8">      
	        <!-- <button id="Submit"  type="submit">Login</button> -->
	          
	        <input type="submit" name="login" value="Login" class="btn btn-info btn-md">
	        <!-- <a href="front" class="btn btn-info btn-md">New Design</a>  -->
	      </div>
	    </div>
	  </fieldset>

</div>
</form> 


  
 
</body>
</html>