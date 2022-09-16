<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Add Patient here!</title>
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
		<h3>Add Patient</h3>
		
		<form action="PatientControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>NRIC:</label></td>
						<td><input type="text" name="nric" /></td>
					</tr>
					
					<tr>
						<td><label>NAME:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					
					<tr>
						<td><label>ADDRESS:</label></td>
						<td><input type="text" name="address" /></td>
					</tr>
					
					<tr>
						<td><label>DATE OF BIRTH:</label></td>
						<td><input type="date" name="dateOfBirth" /></td>
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
						<td><input type="number" name="height" step=".01"/></td>
					</tr>
					
					<tr>
						<td><label>WEIGHT (KG):</label></td>
						<td><input type="number" name="weight" step=".01"/></td>
					</tr>
										
					<tr>
						<td><label>BLOOD GROUP:</label></td>
						<td><input type="text" name="bloodGroup" /></td>
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