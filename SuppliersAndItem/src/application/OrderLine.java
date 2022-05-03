package application;

public class OrderLine {
	String ItemNmae;
	String ItemQuantity ;
	String priceLine ;
	public OrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderLine(String itemNmae, String itemQuantity, String priceLine) {
		super();
		ItemNmae = itemNmae;
		ItemQuantity = itemQuantity;
		this.priceLine = priceLine;
	}
	public String getItemNmae() {
		return ItemNmae;
	}
	public void setItemNmae(String itemNmae) {
		ItemNmae = itemNmae;
	}
	public String getItemQuantity() {
		return ItemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		ItemQuantity = itemQuantity;
	}
	public String getPriceLine() {
		return priceLine;
	}
	public void setPriceLine(String priceLine) {
		this.priceLine = priceLine;
	}
	@Override
	public String toString() {
		return "OrderLine [ItemNmae=" + ItemNmae + ", ItemQuantity=" + ItemQuantity + ", priceLine=" + priceLine + "]";
	}
	
}
