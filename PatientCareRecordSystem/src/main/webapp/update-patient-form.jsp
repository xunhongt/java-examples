<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Update Patient here!</title>
	<!--  LINK TO CSS FILE -->
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/form-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Patient List</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Patient</h3>
		
		<form action="PatientControllerServlet" method="POST">
			<input type="hidden" name="command" value="UPDATE" />
			
			<table>
				<tbody>
					<tr>
						<td><label>NRIC:</label></td>
						<td><input type="text" name="nric"
								   value="${THE_PATIENT.nric}" /></td>
					</tr>
					
					<tr>
						<td><label>NAME:</label></td>
						<td><input type="text" name="name"
								   value="${THE_PATIENT.name}"/></td>
					</tr>
					
					<tr>
						<td><label>ADDRESS:</label></td>
						<td><input type="text" name="address" 
								   value="${THE_PATIENT.address}"/></td>						
					</tr>
					
					<tr>
						<td><label>DATE OF BIRTH:</label></td>
						<td><input type="date" name="dateOfBirth"
								   value="${THE_PATIENT.dateOfBirth}"/></td>								
					</tr>
					
					<tr>
						<td><label>GENDER:</label></td>
						<td><select name="gender">													
								<option value="M">Male</option>
								<option value="F">Female</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td><label>HEIGHT (M):</label></td>
						<td><input type="number" name="height" step=".01"
								   value="${THE_PATIENT.height}"/></td>
					</tr>
					
					<tr>
						<td><label>WEIGHT (KG):</label></td>
						<td><input type="number" name="weight" step=".01"
								   value="${THE_PATIENT.weight}"/></td>						
					</tr>
										
					<tr>
						<td><label>BLOOD GROUP:</label></td>
						<td><input type="text" name="bloodGroup"
								   value="${THE_PATIENT.bloodGroup}"/></td>							
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>	
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="PatientControllerServlet">Back to List</a>
		</p>
		
	</div>
</body>

</html>