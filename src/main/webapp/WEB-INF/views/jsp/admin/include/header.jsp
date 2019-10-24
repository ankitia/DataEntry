<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head> 
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap-responsive.css"></c:url>">
  </head>

  <body>
  
  	<c:if test="${userName != null }"> 
      <!-- Fixed navbar -->
      <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Projects</a>
            <div class="nav-collapse collapse">    
              <ul class="nav">  
                <c:choose>
                	<c:when test="${userRole == 1}">
                		<li><a href="<%=request.getContextPath() %>/admindashboard">Dashboard</a></li>
                	</c:when>
                	<c:otherwise> 
                		<li><a href="<%=request.getContextPath() %>/masterDataEntryURL">KFB Email</a></li>
                		<li><a href="<%=request.getContextPath() %>/masterEmailEntryURL">Email-Response</a></li>
                	</c:otherwise>     
                </c:choose>
                                  
                <c:if test="${userRole == 1}">
                	<li><a href="<%=request.getContextPath() %>/report">Report</a></li>
                	<%-- <li><a href="<%=request.getContextPath() %>/userAssigned">Assigned</a></li>
                	<li><a href="<%=request.getContextPath() %>/reset">ReActive</a></li> --%>
                </c:if>
              </ul>   
              <ul class="nav navbar-nav navbar-right" style="float:right;">
			      <li><a href="<%=request.getContextPath() %>/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			    </ul> 
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>
      
      </c:if>
      <c:if test="${userName == null }">
      		<c:redirect url="/"></c:redirect>
      </c:if> 
      
  </body>
</html>