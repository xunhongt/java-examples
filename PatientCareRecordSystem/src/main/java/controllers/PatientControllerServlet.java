package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.PatientDBUtil;
import models.Patient;


@WebServlet("/PatientControllerServlet")
public class PatientControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Calling PatientDBUtil to pull Patients Data from Database
	private PatientDBUtil patientDbUtil;

	//Set Resource Injection (from Context.xml)
	@Resource(name="jdbc/myschema")
	
	//Initialize Data source
	private DataSource dataSource;

	
	/*
	 * Initialize your servlet by overriding the Parent Method init(), and add your own custom functionality
	 */
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// Create the Patient DB Util Object, and pass in the Connection Pool & Data Source
		try {
			patientDbUtil = new PatientDBUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	/*
	 * Main GET Method: Contains the following Helper Methods
	 * 	- listPatients
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// List Patients in MVC Fashion
		//Helper Method --> List Patients 
		try {
			listPatients(request, response);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	
	/*
	 * Main POST Method: Contains the following Helper Methods
	 * 	- addPatients
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// Read the Command parameter
			String theCommand = request.getParameter("command");
			
			// If the command is missing, go back to list Patients
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST": 
				doGet(request, response);
				break;
			
			case "ADD":
				addPatient(request,response);
				break;
				
			default:
				doGet(request,response);
			}
			
		}catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	//listPatients --> List down all the patients queried from Database, and forward the response to list-patients.jsp
	private void listPatients(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Get Patients from DB Util
		List<Patient> patients = patientDbUtil.getPatients();

		// Add Patients to the Request
		request.setAttribute("PATIENT_LIST", patients);
		
		// send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-patients.jsp");
		dispatcher.forward(request, response);
		
	}

	//addPatients --> Add Patient into Database
	private void addPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
		// Read Patient Info from form data
			
			String nric = request.getParameter("nric");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			
			String tempDOB = request.getParameter("dateOfBirth");
			Date dateOfBirth = new SimpleDateFormat("yyyy-mm-dd").parse(tempDOB);
			
			String patientDrugAllergyId = null;
			
			String tempGender = request.getParameter("gender");
			char gender = tempGender.charAt(0);
			
			String tempHeight = request.getParameter("height");
			Float height = Float.parseFloat(tempHeight);
			
			String tempWeight = request.getParameter("weight");
			Float weight = Float.parseFloat(tempWeight);
			
			String bloodGroup = request.getParameter("bloodGroup");		
			
		// Create a new Patient Object
			
			Patient thePatient = new Patient(nric, name, patientDrugAllergyId, address, 
					dateOfBirth, gender, height, weight, bloodGroup);
			
		// Add Patient into Database
			
			patientDbUtil.addPatient(thePatient);
			
		// Send back to main page (the Patient List)
			
			doGet(request,response);
			
		}catch (Exception exc) {
			throw new ServletException(exc);
		}	

	}

}
