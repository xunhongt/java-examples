<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>LIST OF PATIENTS</title>
	
	<!--  LINK TO CSS FILE -->
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


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
					<th>Action</th>																															
				</tr>
		
				<c:forEach var="tempPatient" items="${PATIENT_LIST}">
				
					<!-- Set an Update Link for each patient -->	
					<c:url var="updateLink" value="PatientControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="patientId" value="${tempPatient.nric}" />
					</c:url>
					<!-- Set a Delete Link for each patient -->
					<c:url var="deleteLink" value="PatientControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="patientId" value="${tempPatient.nric}" />
					</c:url>
					
					<tr>	
						<td>${tempPatient.nric}</td>
						<td>${tempPatient.name}</td>
						<td>${tempPatient.gender}</td>
						<td>${tempPatient.dateOfBirth}</td>
						<td>${tempPatient.height}</td>
						<td>${tempPatient.weight}</td>
						<td>${tempPatient.address}</td>
						<td>${tempPatient.bloodGroup}</td>
						<td>
					<!-- Input Javascript code to prompt user if they want to delete the patient -->						
							<a href="${updateLink}">Update</a>
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this Patient?'))) return false">
							Delete</a>
						</td>						
					</tr>
					
				</c:forEach>																										

			</table>
		</div>
	</div>
</body>
</html>