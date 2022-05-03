package application;

public class PurchaseOrder {
	
	
	 int purchaseOrderId;
	 int purchaseOrderprice;// NOT TO FLOATE
	 String purchaseOrderDate ;
	 
	 
	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseOrder(int purchaseOrderId, int purchaseOrderprice, String purchaseOrderDate) {
		super();
		this.purchaseOrderId = purchaseOrderId;
		this.purchaseOrderprice = purchaseOrderprice;
		this.purchaseOrderDate = purchaseOrderDate;
	}
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public int getPurchaseOrderprice() {
		return purchaseOrderprice;
	}
	public void setPurchaseOrderprice(int purchaseOrderprice) {
		this.purchaseOrderprice = purchaseOrderprice;
	}
	public String getPurchaseOrderDate() {
		return purchaseOrderDate;
	}
	public void setPurchaseOrderDate(String purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [purchaseOrderId=" + purchaseOrderId + ", purchaseOrderprice=" + purchaseOrderprice
				+ ", purchaseOrderDate=" + purchaseOrderDate + "]";
	}
	 
	 
	
	

}
