package application;

public class MENU {
	String itemID; 
	String itemName ;
	String itemPrice; 
	String foodType ;
	public MENU() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MENU(String itemID, String itemName, String itemPrice, String foodType) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.foodType = foodType;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemId) {
		this.itemID = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

}
