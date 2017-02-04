<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.model.*" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
 <c:when test="${inEditMode == true}">
   <h3>Edit Person</h3>
   <form:form action="update" method="post" >
            <table border="0">
              <tr>
                    <td>Id:</td>
                    <td><form:input path="personid" value="${p.personid}" /></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name"  value="${p.name}" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><form:input  path="country"  value="${p.country}" /></td>
                </tr>			
                <tr>
                    <td colspan="2" ><input type="submit" value="Edit Person" /></td>
                </tr>
            </table>
        </form:form>
 </c:when>
 <c:otherwise>
<h2>Add a Person</h2>
<p>


 <c:choose>
<c:when test="${inEditMode == true or onceDeEd == true or onceUpdated == true}">		
<c:set var="persons" value="../persons" />
</c:when>
<c:otherwise>
<c:set var="persons" value="persons" />
</c:otherwise>
</c:choose>

<form:form action="${persons}" method="post" >
            <table border="0">
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name"  /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><form:input  path="country"  /></td>
                </tr>			
                <tr>
                    <td colspan="2" ><input type="submit" value="Add Person" /></td>
                </tr>
            </table>
        </form:form>
</p>

</c:otherwise>
</c:choose>

<br/>
<c:choose>
 <c:when test="${empty list}">
   <h3>No Persons Registered</h3>
 </c:when>
 <c:otherwise>
	
   <h3>Persons List</h3>
<div>
        <table border="1" cellpadding="5">
            <tr>
                <th>Person Id</th>
                <th>Person Name</th>
                <th>Person Country</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
      
           
            <c:forEach items="${list}" var="person" >
                <tr>
                    <td>${person.personid}</td>
                    <td>${person.name}</td>
                    <td>${person.country}</td>
                    <c:choose>
 					<c:when test="${inEditMode == true or onceDeEd == true or onceUpdated == true}">
 					<td><a href="../edit/${person.personid}">Edit</a></td>
                   	<td><a href="../delete/${person.personid}">Delete</a></td>
 					</c:when>
 					<c:otherwise>
 					<td><a href="edit/${person.personid}">Edit</a></td>
                    <td><a href="delete/${person.personid}">Delete</a></td>
					</c:otherwise>
					</c:choose>         
                </tr>
            </c:forEach>
         
        </table>
    </div>
</c:otherwise>
</c:choose>

</body>
</html>