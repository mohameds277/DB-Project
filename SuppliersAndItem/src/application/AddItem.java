package application;

public class AddItem {

	
	
			 String ItemName;
			 int ItemPrice;
			 int ItemQuantity;
			 String ItemType;
 
			 
			 
			public AddItem() {
				super();
				// TODO Auto-generated constructor stub
			}



			public AddItem(String itemName, int itemPrice, int itemQuantity, String itemType) {
				super();
				ItemName = itemName;
				ItemPrice = itemPrice;
				ItemQuantity = itemQuantity;
				ItemType = itemType;
			}



		



			public String getItemName() {
				return ItemName;
			}



			public void setItemName(String itemName) {
				ItemName = itemName;
			}



			public int getItemPrice() {
				return ItemPrice;
			}



			public void setItemPrice(int itemPrice) {
				ItemPrice = itemPrice;
			}



			public int getItemQuantity() {
				return ItemQuantity;
			}



			public void setItemQuantity(int itemQuantity) {
				ItemQuantity = itemQuantity;
			}



			public String getItemType() {
				return ItemType;
			}



			public void setItemType(String itemType) {
				ItemType = itemType;
			}



			@Override
			public String toString() {
				return "Item [ItemName=" + ItemName + ", ItemPrice=" + ItemPrice
						+ ", ItemQuantity=" + ItemQuantity + ", ItemType=" + ItemType + "]";
			}
			
			 
			
}
