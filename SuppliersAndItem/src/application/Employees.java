package application;

public class Employees {
	public int EmployeeId;
	public String EmployeeName; 
	public String EmployeePhone;
	public String EmployeeSalary;
	public String EmployeeAddress;
	public String EmployeeGender;
	 public String EmployeeDateOfBirth;
	 public String EmployeeDateOfEmployment;
	 public String EmployeeDateOfResignation;
	 public String EmployeeDepartmentName;
	 public	 int BranchWorkId;
	 
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees(int employeeId, String employeeName, String employeePhone, String employeeSalary,
			String employeeAddress, String employeeGender, String employeeDateOfBirth,
			String employeeDateOfEmployment, String employeeDateOfResignation, String employeeDepartmentName,
			int branchWorkId) {
		super();
		EmployeeId = employeeId;
		EmployeeName = employeeName;
		EmployeePhone = employeePhone;
		EmployeeSalary = employeeSalary;
		EmployeeAddress = employeeAddress;
		EmployeeGender = employeeGender;
		EmployeeDateOfBirth = employeeDateOfBirth;
		EmployeeDateOfEmployment = employeeDateOfEmployment;
		EmployeeDateOfResignation = employeeDateOfResignation;
		EmployeeDepartmentName = employeeDepartmentName;
		BranchWorkId = branchWorkId;
	}

	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeePhone() {
		return EmployeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		EmployeePhone = employeePhone;
	}

	public String getEmployeeSalary() {
		return EmployeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		EmployeeSalary = employeeSalary;
	}

	public String getEmployeeAddress() {
		return EmployeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		EmployeeAddress = employeeAddress;
	}

	public String getEmployeeGender() {
		return EmployeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		EmployeeGender = employeeGender;
	}

	public String getEmployeeDateOfBirth() {
		return EmployeeDateOfBirth;
	}

	public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
		EmployeeDateOfBirth = employeeDateOfBirth;
	}

	public String getEmployeeDateOfEmployment() {
		return EmployeeDateOfEmployment;
	}

	public void setEmployeeDateOfEmployment(String employeeDateOfEmployment) {
		EmployeeDateOfEmployment = employeeDateOfEmployment;
	}

	public String getEmployeeDateOfResignation() {
		return EmployeeDateOfResignation;
	}

	public void setEmployeeDateOfResignation(String employeeDateOfResignation) {
		EmployeeDateOfResignation = employeeDateOfResignation;
	}

	public String getEmployeeDepartmentName() {
		return EmployeeDepartmentName;
	}

	public void setEmployeeDepartmentName(String employeeDepartmentName) {
		EmployeeDepartmentName = employeeDepartmentName;
	}

	public int getBranchWorkId() {
		return BranchWorkId;
	}

	public void setBranchWorkId(int branchWorkId) {
		BranchWorkId = branchWorkId;
	}

	@Override
	public String toString() {
		return "Employees [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", EmployeePhone="
				+ EmployeePhone + ", EmployeeSalary=" + EmployeeSalary + ", EmployeeAddress=" + EmployeeAddress
				+ ", EmployeeGender=" + EmployeeGender + ", EmployeeDateOfBirth=" + EmployeeDateOfBirth
				+ ", EmployeeDateOfEmployment=" + EmployeeDateOfEmployment + ", EmployeeDateOfResignation="
				+ EmployeeDateOfResignation + ", EmployeeDepartmentName=" + EmployeeDepartmentName
				+ ", BranchWorkId=" + BranchWorkId + "]";
	}
	
}
