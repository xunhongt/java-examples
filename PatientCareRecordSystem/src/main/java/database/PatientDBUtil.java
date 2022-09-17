package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import javax.sql.DataSource;
import models.Patient;

public class PatientDBUtil {
	
	//Declaring a DataSource field (from javax.sql.DataSource) called datasource
	private DataSource dataSource;
	
	public PatientDBUtil(DataSource thisDataSource) {
		dataSource = thisDataSource;
	}
	
	//Return a List of Patient Classes after querying from Database
	public List<Patient> getPatients() throws Exception {
		
		List<Patient> patients = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStatement = null;
		ResultSet myRs = null;
		
		try {
			//1. Get a Connection
			myConn = dataSource.getConnection();
			
			//2. Create a SQL Statement
			String sql = "SELECT * FROM patients ORDER BY nric DESC";
			myStatement = myConn.createStatement();
			
			//3. Execute a Query
			myRs = myStatement.executeQuery(sql);
			
			//4. Process Result Set
			while (myRs.next()) {
				// Retrieve data from Result Set Row
				String nric = myRs.getString("nric");
				String name = myRs.getString("name");
				String patientDrugAllergyId = myRs.getString("patientDrugAllergyId");
				String address = myRs.getString("address");
				Date dateOfBirth = myRs.getDate("dateOfBirth");
				char gender = myRs.getString("gender").charAt(0);
				/*
				 * if (genderCode.toLower() = 'M') {
				 * 		String gender = "Male"
				 * } elseif (genderCode.toLower() = 'F') {
				 * 		String gender = "Male"
				 * } 
				 */
				float height = myRs.getFloat("height");
				float weight = myRs.getFloat("weight");
				String bloodGroup = myRs.getString("bloodGroup");					
				
				// Create new Patient Object
				
				Patient tempPatient = new Patient(nric, name, patientDrugAllergyId, address, 
						dateOfBirth, gender, height, weight, bloodGroup);
				
				// Add it to list of Patients
				patients.add(tempPatient);
			}
			
			//5. Close Connection
			return patients;
		} 
		finally {
			close(myConn, myStatement, myRs);
		}
	}


	//Method to Add Patient into Database
	public void addPatient(Patient thePatient) throws Exception{

		Connection myConn = null;
		PreparedStatement myStatement = null;
		
		try {
			//Create SQL for insert
			myConn = dataSource.getConnection();
			
			//Set Parameter Values for the Patient
			
			/*
			 * SQL String is created using the PreparedStatement format (Feature used to execute the same SQL statement
			 * repeatedly with high efficiency)
			 * 
			 * Certain values are left unspecified, called parameters (labeled '?')
			 */
			String sql = "INSERT INTO patients " + "(nric, name, patientDrugAllergyId, address, "
					+ "gender, dateofBirth, height, weight, bloodGroup)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStatement = myConn.prepareStatement(sql);
			
			myStatement.setString(1, thePatient.getNric());
			myStatement.setString(2, thePatient.getName());
			myStatement.setString(3, thePatient.getPatientDrugAllergyId());
			myStatement.setString(4, thePatient.getAddress());
			myStatement.setString(5, String.valueOf(thePatient.getGender()));
			myStatement.setDate(6,  new java.sql.Date(thePatient.getDateOfBirth().getTime()));
			myStatement.setFloat(7, thePatient.getHeight());
			myStatement.setFloat(8, thePatient.getWeight());
			myStatement.setString(9, thePatient.getBloodGroup());		
			
			//Execute SQL Insert
			myStatement.execute();

		}finally {
			//Clean Up JDBC Objects
			close(myConn, myStatement, null);
		}

	}

	//Method to Get Patient from Database
	public Patient getPatient(String thePatientId) throws Exception {

		Patient thePatient = null;

		Connection myConn = null;
		PreparedStatement myStatement = null;
		ResultSet myRs = null;
		
		try {
			// Get Connection from Database
			myConn = dataSource.getConnection();
			
			// Create SQL to get selected patient
			String sql = "SELECT * FROM patients WHERE nric=?";
			
			// Create Prepared Statement 
			myStatement = myConn.prepareStatement(sql);
			
			// Set Parameters
			myStatement.setString(1, thePatientId);
			
			// Execute Statement
			myRs = myStatement.executeQuery();
			
			// Retrieve data from result set row
			if (myRs.next()) {
				// Retrieve data from Result Set Row
				String nric = myRs.getString("nric");
				String name = myRs.getString("name");
				String patientDrugAllergyId = myRs.getString("patientDrugAllergyId");
				String address = myRs.getString("address");
				Date dateOfBirth = myRs.getDate("dateOfBirth");
				char gender = myRs.getString("gender").charAt(0);
				/*
				 * if (genderCode.toLower() = 'M') {
				 * 		String gender = "Male"
				 * } elseif (genderCode.toLower() = 'F') {
				 * 		String gender = "Male"
				 * } 
				 */
				float height = myRs.getFloat("height");
				float weight = myRs.getFloat("weight");
				String bloodGroup = myRs.getString("bloodGroup");					
				
				// Create new Patient Object
				
				thePatient = new Patient(thePatientId, name, patientDrugAllergyId, address, 
						dateOfBirth, gender, height, weight, bloodGroup);
			}
			else {
				throw new Exception("Cannot find Patient ID: " + thePatientId);
			}
			
			return thePatient;
			
		}finally {
			//Clean Up JDBC Objects
			close(myConn, myStatement, myRs);
		}
	}
	
	//Method to Close Connection
	private void close(Connection myConn, Statement myStatement, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStatement != null) {
				myStatement.close();
			}
			// Closing Database Connection just puts it back to the Connection Pool
			if (myConn != null) {
				myConn.close();
			}
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
