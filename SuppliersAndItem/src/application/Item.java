package application;

public class Item {

	
	
			 int ItemId;
			 String ItemName;
			 int ItemPrice;
			 int ItemQuantity;
			 String ItemType;
 
			 
			 
			public Item() {
				super();
				// TODO Auto-generated constructor stub
			}



			public Item(int itemId, String itemName, int itemPrice, int itemQuantity, String itemType) {
				super();
				ItemId = itemId;
				ItemName = itemName;
				ItemPrice = itemPrice;
				ItemQuantity = itemQuantity;
				ItemType = itemType;
			}



			public int getItemId() {
				return ItemId;
			}



			public void setItemId(int itemId) {
				ItemId = itemId;
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
				return "Item [ItemId=" + ItemId + ", ItemName=" + ItemName + ", ItemPrice=" + ItemPrice
						+ ", ItemQuantity=" + ItemQuantity + ", ItemType=" + ItemType + "]";
			}
			
			 
			
}
