package application;

public class information {
	String CustomerName;
	String CustomerPhone;
	String CustomerAddress;
	String ItemQuantity;
	public information() {
		super();
		// TODO Auto-generated constructor stub
	}
	public information(String customerName, String customerPhone, String customerAddress, String itemQuantity) {
		super();
		CustomerName = customerName;
		CustomerPhone = customerPhone;
		CustomerAddress = customerAddress;
		ItemQuantity = itemQuantity;
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
	public String getItemQuantity() {
		return ItemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		ItemQuantity = itemQuantity;
	}

}
