package application;

public class MakePurchaseOrder {
	
			 String BranchNameByLocation;

			 int purchaseOrderId;
			 int purchaseOrderprice;
			 String purchaseOrderDate ;

			 
			 int EmployeeResponseId;
			 String EmployeeResponseName;
			 
			 String SupplierName;
			 String SupplierphoneNumb;
			 
			 MakePurchaseOrder() {
				super();
				// TODO Auto-generated constructor stub
			}

			

			public MakePurchaseOrder(String branchNameByLocation, int purchaseOrderId, int purchaseOrderprice,
					String purchaseOrderDate, int employeeResponseId, String employeeResponseName, String supplierName,
					String supplierphoneNumb) {
				super();
				BranchNameByLocation = branchNameByLocation;
				this.purchaseOrderId = purchaseOrderId;
				this.purchaseOrderprice = purchaseOrderprice;
				this.purchaseOrderDate = purchaseOrderDate;
				EmployeeResponseId = employeeResponseId;
				EmployeeResponseName = employeeResponseName;
				SupplierName = supplierName;
				SupplierphoneNumb = supplierphoneNumb;
			}



			public String getBranchNameByLocation() {
				return BranchNameByLocation;
			}

			public void setBranchNameByLocation(String branchNameByLocation) {
				BranchNameByLocation = branchNameByLocation;
			}

			public int getPurchaseOrderId() {
				return purchaseOrderId;
			}

			public void setPurchaseOrderId(int purchaseOrderId) {
				this.purchaseOrderId = purchaseOrderId;
			}

			public String getPurchaseOrderDate() {
				return purchaseOrderDate;
			}

			public void setPurchaseOrderDate(String purchaseOrderDate) {
				this.purchaseOrderDate = purchaseOrderDate;
			}

			public float getPurchaseOrderprice() {
				return purchaseOrderprice;
			}

			
			public int getEmployeeResponseId() {
				return EmployeeResponseId;
			}

			public void setEmployeeResponseId(int employeeResponseId) {
				EmployeeResponseId = employeeResponseId;
			}

			public String getEmployeeResponseName() {
				return EmployeeResponseName;
			}

			public void setEmployeeResponseName(String employeeResponseName) {
				EmployeeResponseName = employeeResponseName;
			}

			public String getSupplierName() {
				return SupplierName;
			}

			public void setSupplierName(String supplierName) {
				SupplierName = supplierName;
			}

			public String getSupplierphoneNumb() {
				return SupplierphoneNumb;
			}

			public void setSupplierphoneNumb(String supplierphoneNumb) {
				SupplierphoneNumb = supplierphoneNumb;
			}

			@Override
			public String toString() {
				return "MakePurchaseOrder [BranchNameByLocation=" + BranchNameByLocation + ", purchaseOrderId="
						+ purchaseOrderId + ", purchaseOrderDate=" + purchaseOrderDate + ", purchaseOrderprice="
						+ purchaseOrderprice + ", EmployeeResponseId=" + EmployeeResponseId + ", EmployeeResponseName="
						+ EmployeeResponseName + ", SupplierName=" + SupplierName + ", SupplierphoneNumb="
						+ SupplierphoneNumb + "]";
			}

			

}
