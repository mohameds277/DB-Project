package application;

public class PurchaseOrderLine {
	
	
			 int purchaseOrderId; 
			 int ItemId;
			 int ItemQuantity;
			 float priceLine;
			public PurchaseOrderLine() {
				super();
				// TODO Auto-generated constructor stub
			}
			public PurchaseOrderLine(int purchaseOrderId, int itemId, int itemQuantity, float priceLine) {
				super();
				this.purchaseOrderId = purchaseOrderId;
				ItemId = itemId;
				ItemQuantity = itemQuantity;
				this.priceLine = priceLine;
			}
			public int getPurchaseOrderId() {
				return purchaseOrderId;
			}
			public void setPurchaseOrderId(int purchaseOrderId) {
				this.purchaseOrderId = purchaseOrderId;
			}
			public int getItemId() {
				return ItemId;
			}
			public void setItemId(int itemId) {
				ItemId = itemId;
			}
			public int getItemQuantity() {
				return ItemQuantity;
			}
			public void setItemQuantity(int itemQuantity) {
				ItemQuantity = itemQuantity;
			}
			public float getPriceLine() {
				return priceLine;
			}
			public void setPriceLine(float priceLine) {
				this.priceLine = priceLine;
			}
			@Override
			public String toString() {
				return "PurchaseOrderLine [purchaseOrderId=" + purchaseOrderId + ", ItemId=" + ItemId
						+ ", ItemQuantity=" + ItemQuantity + ", priceLine=" + priceLine + "]";
			}
			 
			 
			 
			


}
