package models;

import java.util.Date;

public class Patient {
	
	//Defining the fields required for patient class 
	private String nric;
	private String name;
	private String patientDrugAllergyId;
	private String address;
	private Date dateOfBirth;
	private char gender;
	private float height;
	private float weight;
	private String bloodGroup;
	
	/* Constructing the Patient Object
	  - Highlight the fields, right click 
	  - Select Source --> Generate Constructor from fields
	
	*/
	public Patient(String nric, String name, String patientDrugAllergyId, String address, Date dateOfBirth,
			char gender, float height, float weight, String bloodGroup) {
		super();
		this.nric = nric;
		this.name = name;
		this.patientDrugAllergyId = patientDrugAllergyId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.bloodGroup = bloodGroup;
	}
	
	/* Defining getters and setters 
	  - Can set/get the fields for the Student Class
	*/	
	
	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatientDrugAllergyId() {
		return patientDrugAllergyId;
	}

	public void setPatientDrugAllergyId(String patientDrugAllergyId) {
		this.patientDrugAllergyId = patientDrugAllergyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	
	//For debugging purposes --> This allows you to dump the Patient Class details as output 
	@Override
	public String toString() {
		return "Patient [nric=" + nric + ", name=" + name + ", patientDrugAllergyId=" + patientDrugAllergyId
				+ ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", height=" + height
				+ ", weight=" + weight + ", bloodGroup=" + bloodGroup + "]";
	}

}

