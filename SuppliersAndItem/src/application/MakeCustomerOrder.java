package application;

public class MakeCustomerOrder {
	String EmployeeResponseID ;
	String OrderID ;
	String CustomerID;
	public MakeCustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MakeCustomerOrder(String employeeResponseID, String orderID, String customerID) {
		super();
		EmployeeResponseID = employeeResponseID;
		OrderID = orderID;
		CustomerID = customerID;
	}
	public String getEmployeeResponseID() {
		return EmployeeResponseID;
	}
	public void setEmployeeResponseID(String employeeResponseID) {
		EmployeeResponseID = employeeResponseID;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	
	
	

}
