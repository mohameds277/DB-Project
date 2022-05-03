package application;

public class CustomerOrder {
	String OrderID ;
	String OrderDate;
	String OrderPrice ;
	String DeliveryMethod ;
	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerOrder(String orderID, String orderDate, String orderPrice, String deliveryMethod) {
		super();
		OrderID = orderID;
		OrderDate = orderDate;
		OrderPrice = orderPrice;
		DeliveryMethod = deliveryMethod;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		OrderPrice = orderPrice;
	}
	public String getDeliveryMethod() {
		return DeliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		DeliveryMethod = deliveryMethod;
	}
	
	

}
