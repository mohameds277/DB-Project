package application;

public class Customer {
	String CustomerID ;
	String CustomerName ;
	String CustomerPhone;
	String CustomerAddress;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerID, String customerName, String customerPhone,
			String customerAddress) {
		super();
		CustomerID = customerID;
		CustomerName = customerName;
		CustomerPhone = customerPhone;
		CustomerAddress = customerAddress;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getCustomerPhone() {
		return CustomerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		CustomerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return CustomerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		CustomerAddress = customerAddress;
	}
	
	

}
