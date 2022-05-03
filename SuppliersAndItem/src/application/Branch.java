package application;

public class Branch {
public int BranchId;
public String location;
public String nameByLocation;
public String phoneNumber;
public String Email;
public int numberOfEmployees;
public String manger;
public Branch() {
	super();
	// TODO Auto-generated constructor stub
}
public Branch(int branchId, String location, String nameByLocation, String phoneNumber, String email,
		int numberOfEmployees, String manger) {
	super();
	BranchId = branchId;
	this.location = location;
	this.nameByLocation = nameByLocation;
	this.phoneNumber = phoneNumber;
	Email = email;
	this.numberOfEmployees = numberOfEmployees;
	this.manger = manger;
}
public int getBranchId() {
	return BranchId;
}
public void setBranchId(int branchId) {
	BranchId = branchId;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getNameByLocation() {
	return nameByLocation;
}
public void setNameByLocation(String nameByLocation) {
	this.nameByLocation = nameByLocation;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public int getNumberOfEmployees() {
	return numberOfEmployees;
}
public void setNumberOfEmployees(int numberOfEmployees) {
	this.numberOfEmployees = numberOfEmployees;
}
public String getManger() {
	return manger;
}
public void setManger(String manger) {
	this.manger = manger;
}




}
