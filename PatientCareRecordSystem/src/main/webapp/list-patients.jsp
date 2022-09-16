<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.Date, java.util.*, controllers.*, models.*, database.*" %>
<html>
<head>
	<title>LIST OF PATIENTS</title>
	
	<!--  LINK TO CSS FILE -->
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
	// Get List of Students from the Request Object (sent by servlet)
	// Downcast the object to List<Patient>, and get Attribute for the Request
	
	List<Patient> thePatients = (List<Patient>) request.getAttribute("PATIENT_LIST");
%>


<body>

	<!--  ID points to the CSS file -->
	<div id="wrapper">
		<div id="header">
			<h2>Patient List</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
		<!--  PUT NEW BUTTON: Add Patient -->
		
		<input type="button" value="Add Patient"
			   onclick="window.location.href='add-patient-form.jsp'; return false;"
			   class="add-patient-button"
		/>
		
		<br>
		<br>
			<table>
				<tr>
					<th>NRIC</th>
					<th>Name</th>
					<th>Gender</th>					
					<th>Date of Birth (YYYY-MM-DD)</th>
					<th>Height (M)</th>	
					<th>Weight (KG)</th>	
					<th>Address</th>	
					<th>Blood Group</th>																												
				</tr>
					<% for (Patient tempPatient : thePatients) { %>				
				<tr>
					<td><%= tempPatient.getNric() %></td>
					<td><%= tempPatient.getName() %></td>
					<td><%= tempPatient.getGender() %></td>
					<td><%= tempPatient.getDateOfBirth() %></td>
					<td><%= tempPatient.getHeight() %></td>
					<td><%= tempPatient.getWeight() %></td>
					<td><%= tempPatient.getAddress() %></td>
					<td><%= tempPatient.getBloodGroup() %></td>																																				
				</tr>
					<% } %>
			</table>
		</div>
	</div>
</body>
</html>