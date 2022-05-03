package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SecondControlar {
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234db";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Villa_pizza";
	private Connection con;
	private ObservableList<Employees> dataList= FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TabPane tapPaneMain;

	@FXML
	private Tab EmployeeTab;

	@FXML
	private TableView<Employees> Emptable;

	@FXML
	private TableColumn<Employees, String> EmployeeName;

	@FXML
	private TableColumn<Employees, String> EmployeeGender;

	@FXML
	private TableColumn<Employees, String> EmployeeSalary;

	@FXML
	private TableColumn<Employees, String> EmployeeDepartmentName;

	@FXML
	private TableColumn<Employees, String> PhoneNumber;

	@FXML
	private GridPane GridPaneEmp;

	@FXML
	private TextField EmpName;

	@FXML
	private TextField EmpId;

	@FXML
	private TextField EmpPhone;

	@FXML
	private TextField EmpSalary;

	@FXML
	private TextField EmpAddress;

	@FXML
	private TextField EmpGender;

	@FXML
	private TextField empDOB;

	@FXML
	private TextField DOE;

	@FXML
	private TextField EmpBranchID;

	@FXML
	private TextField EmpDName;
	
	@FXML
	private TextField departmentText;
	
	@FXML
	private TextField departmentButton;
	
	@FXML
	private TextField srarchNmaeText;

	@FXML
	void initialize() throws ClassNotFoundException, SQLException {
		connectDB();
		getEmployees();
		Emptable.setEditable(true);
		edit();
		getPurchaseOrdersTable();
		getBranches();
		BranchTable.setEditable(true);
		
		getMENU();
		getCustomer();
		getCustomerOrder();

	}

	public void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}

	public void getEmployees() throws SQLException, ClassNotFoundException {

		String mystring = "select * from Employees";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);

		while (rs.next()) {
			dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
					+ Integer.parseInt(rs.getString(11)));

		}
		System.out.println(dataList.isEmpty());

		EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
		EmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
		EmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
		EmployeeDepartmentName.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
		PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));

		Emptable.setItems(dataList);
		System.out.println(dataList.isEmpty());
	
		


	}

	@FXML
	void ViewGrid(MouseEvent event) {
		GridPaneEmp.setVisible(true);

		Employees e = Emptable.getSelectionModel().getSelectedItem();

		EmpName.setText(e.getEmployeeName());
		//EmpId.setText(e.getEmployeeId() + "");
		EmpPhone.setText(e.getEmployeePhone());
		EmpSalary.setText(e.getEmployeeSalary() + "");
		EmpAddress.setText(e.getEmployeeAddress());
		EmpGender.setText(e.getEmployeeGender());
		empDOB.setText(e.getEmployeeDateOfBirth());
		DOE.setText(e.getEmployeeDateOfEmployment());
		EmpBranchID.setText(e.getBranchWorkId() + "");
		EmpDName.setText(e.getEmployeeDepartmentName());

	}

	void edit() {
		EmployeeName.setCellFactory(TextFieldTableCell.forTableColumn());
		EmployeeName.setOnEditCommit(e -> {
			e.getTableView().getItems().get(Emptable.getSelectionModel().getSelectedIndex())
					.setEmployeeName(e.getNewValue());
			try {
				update(Emptable.getSelectionModel().getSelectedItem());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});

		EmployeeGender.setCellFactory(TextFieldTableCell.forTableColumn());
		EmployeeGender.setOnEditCommit(e -> {
			e.getTableView().getItems().get(Emptable.getSelectionModel().getSelectedIndex())
					.setEmployeeGender(e.getNewValue());
			try {
				update(Emptable.getSelectionModel().getSelectedItem());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});

		EmployeeSalary.setCellFactory(TextFieldTableCell.forTableColumn());
		EmployeeSalary.setOnEditCommit(e -> {
			e.getTableView().getItems().get(Emptable.getSelectionModel().getSelectedIndex())
					.setEmployeeSalary(e.getNewValue());
			try {
				update(Emptable.getSelectionModel().getSelectedItem());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});

		EmployeeDepartmentName.setCellFactory(TextFieldTableCell.forTableColumn());
		EmployeeDepartmentName.setOnEditCommit(e -> {
			e.getTableView().getItems().get(Emptable.getSelectionModel().getSelectedIndex())
					.setEmployeeDepartmentName(e.getNewValue());
			try {
				update(Emptable.getSelectionModel().getSelectedItem());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});
		PhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		PhoneNumber.setOnEditCommit(e -> {
			e.getTableView().getItems().get(Emptable.getSelectionModel().getSelectedIndex())
					.setEmployeePhone(e.getNewValue());
			try {
				update(Emptable.getSelectionModel().getSelectedItem());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});

	}

	private void update(Employees selectedItem) throws SQLException {
		ExecuteStatement("update  Employees set EmployeePhone = " + '"' + selectedItem.getEmployeePhone() + '"' + ","
				+ "EmployeeDepartmentName = " + '"' + selectedItem.getEmployeeDepartmentName() + '"' + ","
				+ "EmployeeSalary = " + '"' + selectedItem.getEmployeeSalary() + '"' + "," + "EmployeeGender = " + '"'
				+ selectedItem.getEmployeeGender() + '"' + "," + "EmployeeName = " + '"'
				+ selectedItem.getEmployeeName() + '"' + " where EmployeeId = " + selectedItem.getEmployeeId() + ";");

//		ExecuteStatement("update  Employees set EmployeePhone = " + '"' + event.getNewValue() + '"'
//		+ " where EmployeeId = " + Emptable.getSelectionModel().getSelectedItem().getEmployeeId() + ";");

	}

	public void refreshTable() {
		try {
			dataList.clear();

			String mystring = "select * from Employees";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(mystring);
			while (rs.next()) {
				dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

				// one tuple at a time
				Emptable.setItems(dataList);
			}

		} catch (SQLException ex) {
			// TODO: handle exception
		}

	}

	@FXML
	void addEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
		connectDB();
		String mystring = "Insert into Employees(EmployeeName,EmployeePhone,EmployeeSalary,EmployeeAddress,EmployeeGender,EmployeeDateOfBirth,EmployeeDateOfEmployment,EmployeeDateOfResignation,EmployeeDepartmentName,BranchWorkId)  values('"+EmpName.getText()+ "'," + '"'
				+ EmpPhone.getText() + '"' + "," + '"' + EmpSalary.getText() + '"' + "," + '"' + EmpAddress.getText()
				+ '"' + "," + '"' + EmpGender.getText() + '"' + "," + '"' + empDOB.getText() + '"' + "," + '"'
				+ DOE.getText() + '"' + ","+ null +"," + '"' + EmpDName.getText() + '"' + "," + '"'
				+ EmpBranchID.getText() + '"' + ");";

		System.out.println(mystring);
//ExecuteStatement("Insert into Suppliers  values("+Integer.parseInt(idtxt.getText())+","+nametxt.getText()+","+phonetxt.getText()+","+emailtxt.getText()+","+locationtxt.getText());
		Statement stmt = con.createStatement();
		stmt.execute(mystring);

		refreshTable();
		String str="update Branch set numberOfEmployees=numberOfEmployees+1 where BranchId ="+EmpBranchID.getText()+";";
		Statement stmt12 = con.createStatement();
		stmt12.execute(str);
		refreshTableBrc();
	}

	@FXML
	void DeletEmpFun(ActionEvent event) throws ClassNotFoundException, SQLException {
		connectDB();
		Employees e = Emptable.getSelectionModel().getSelectedItem();

		String mystring = "delete from  Employees where EmployeeId ="  + e.getEmployeeId()  + ";";		
		System.out.println(mystring);
//delete from  Suppliers where name ="rishonPizzaSupplyer";
		Statement stmt = con.createStatement();
		stmt.execute(mystring);

		refreshTable();
		String str="update Branch set numberOfEmployees=numberOfEmployees-1 where BranchId ="+EmpBranchID.getText()+";";
		Statement stmt12 = con.createStatement();
		stmt12.execute(str);
		refreshTableBrc();

	}

	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}

	}
	
	
	public void EmployeeStealEmployment() throws SQLException{

	String mystring = "select * from Employees where EmployeeDateOfResignation IS  NULL";

	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(mystring);

	while (rs.next()) {
		dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
				rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

		System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
				+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
				+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
				+ Integer.parseInt(rs.getString(11)));

	}
	System.out.println(dataList.isEmpty());

	EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
	EmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
	EmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
	EmployeeDepartmentName.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
	PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));

	Emptable.setItems(dataList);
	};


	public void EmployeeResignation() throws SQLException{

	String mystring = "select * from Employees where EmployeeDateOfResignation IS not NULL";

	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(mystring);

	while (rs.next()) {
		dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
				rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

		System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
				+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
				+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
				+ Integer.parseInt(rs.getString(11)));

	}
	System.out.println(dataList.isEmpty());

	EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
	EmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
	EmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
	EmployeeDepartmentName.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
	PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));

	Emptable.setItems(dataList);
	};
	
	
	
	
	public void EmployeesInformation(String name) throws SQLException{
		String EmployeeInfo = "select * from Employees where EmployeeName =" + '"' + name + '"' + ";";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(EmployeeInfo);

		while (rs.next()) {
			dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
					+ Integer.parseInt(rs.getString(11)));

		}
		System.out.println(dataList.isEmpty());

		EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
		EmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
		EmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
		EmployeeDepartmentName.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
		PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));

		Emptable.setItems(dataList);
		}
	
	
	
	
	public void EmployeesInDepartmentInformation(String department) throws SQLException{
		String EmployeeInfo = "select * from Employees where EmployeeDepartmentName =" + '"' + department + '"' + ";"; 
		System.out.println("1");
		Statement stmt = con.createStatement(); 
		System.out.println("2");
		ResultSet rs = stmt.executeQuery(EmployeeInfo);
		System.out.println("3");

		while (rs.next()) {
			dataList.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
					+ Integer.parseInt(rs.getString(11)));

		}
		System.out.println(dataList.isEmpty());

		EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
		EmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
		EmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
		EmployeeDepartmentName.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
		PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));
		System.out.println("in test"+dataList.isEmpty());

		Emptable.setItems(dataList);
		}
	
	
	
	public void showEmpInDepartment() throws SQLException 
	{
		dataList.clear();
		String department = departmentText.getText();
		EmployeesInDepartmentInformation(department);
		departmentText.setText("");
		
	}
	
	public void srarchEmpByNmae() throws SQLException 
	{
		dataList.clear();
		String EmpName = srarchNmaeText.getText();
		EmployeesInformation(EmpName);
		srarchNmaeText.setText("");
		
	}
	
	
	public void showEmployeesResignation() throws SQLException 
	{
		dataList.clear();
		EmployeeResignation();
		
	}
	
	public void showEmployeesStealEmployment() throws SQLException 
	{
		dataList.clear();
		EmployeeStealEmployment();
	}
	
	
	public void showEmployeesButton() throws ClassNotFoundException, SQLException
	{
		dataList.clear();
		getEmployees();

	}
	
	
	
	
	////////// Purchase Orders Information
	
	@FXML
	private TableView<MakePurchaseOrder> PurchaseOrdersTable;
	
    @FXML
    private TableColumn<MakePurchaseOrder,String> BranchName;

    @FXML
    private TableColumn<MakePurchaseOrder,String> EmpResponseName;

    @FXML
    private TableColumn<MakePurchaseOrder,String> EmpResponseNumber;

    @FXML
    private TableColumn<MakePurchaseOrder,String> PONumber;

    @FXML
    private TableColumn<MakePurchaseOrder,String> POprice;
    
    @FXML
    private TableColumn<MakePurchaseOrder,String> PODate;

    @FXML
    private TableColumn<MakePurchaseOrder,String> SupplierName;

    @FXML
    private TableColumn<MakePurchaseOrder,String> SupplierPhoneNumber;
    
    
	private ObservableList<MakePurchaseOrder> PurchaseOrderslist = FXCollections.observableArrayList();
	
	
	public void getPurchaseOrdersTable() throws SQLException, ClassNotFoundException {
		System.out.println(PurchaseOrderslist.isEmpty());

		String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId ";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);


		while (rs.next()) {
			PurchaseOrderslist.add(new MakePurchaseOrder(rs.getString(1),Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
					rs.getString(4),Integer.parseInt(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8)));

			System.out.println(rs.getString(1) + " " + Integer.parseInt(rs.getString(2)) + " " + Integer.parseInt(rs.getString(3)) + " "
					+ rs.getString(4) + " " + Integer.parseInt(rs.getString(5)) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8));

		}
		System.out.println(PurchaseOrderslist.isEmpty());

		BranchName.setCellValueFactory(new PropertyValueFactory<>("BranchNameByLocation"));
		PONumber.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderId"));
		POprice.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderprice"));
		PODate.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderDate"));
		EmpResponseNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseId"));
		EmpResponseName.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseName"));
		SupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
		SupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("SupplierphoneNumb"));

		PurchaseOrdersTable.setItems(PurchaseOrderslist);
		System.out.println(PurchaseOrderslist.isEmpty());
	


	}



    
    ///Item Table
    @FXML
    private TableColumn<Item,String> ItemNmae;

    @FXML
    private TableColumn<Item,String> ItemPrice;

    @FXML
    private TableColumn<Item,String> ItemQuantity;

    @FXML
    private TableView<Item> ItemTable;

    @FXML
    private TableColumn<Item,String> ItemType;
    
	private ObservableList<Item> Itemlist = FXCollections.observableArrayList();


    
    @FXML
	void ViewItems(MouseEvent event) throws SQLException {
    	Itemlist.clear();
    	MakePurchaseOrder po =PurchaseOrdersTable.getSelectionModel().getSelectedItem();
    	int POid=po.getPurchaseOrderId();
    	System.out.println(Itemlist.isEmpty());

        String mystring = "select e.ItemId,e.ItemName,e.ItemPrice,pl.ItemQuantity,e.ItemType from purchaseOrderLine pl, Item e where pl.purchaseOrderId="+ POid +" and pl.ItemId=e.ItemId";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);


		while (rs.next()) {
			Itemlist.add(new Item(Integer.parseInt(rs.getString(1)),rs.getString(2),
					Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)), rs.getString(5)));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + Integer.parseInt(rs.getString(3)) + " "
					+ rs.getString(4) + " " + rs.getString(5));

		}
		System.out.println(Itemlist.isEmpty());

		ItemNmae.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
		ItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
		ItemQuantity.setCellValueFactory(new PropertyValueFactory<>("ItemQuantity"));
		ItemType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
		
		ItemTable.setItems(Itemlist);	
    	
    }
  
    

    
    
    public void pOByBranchName(String BranchNamee) throws SQLException{
    	String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  b.nameByLocation=" + '"' + BranchNamee + '"' + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);


		while (rs.next()) {
			PurchaseOrderslist.add(new MakePurchaseOrder(rs.getString(1),Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
					rs.getString(4),Integer.parseInt(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8)));

			System.out.println(rs.getString(1) + " " + Integer.parseInt(rs.getString(2)) + " " + Integer.parseInt(rs.getString(3)) + " "
					+ rs.getString(4) + " " + Integer.parseInt(rs.getString(5)) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8));

		}
		System.out.println(PurchaseOrderslist.isEmpty());

		BranchName.setCellValueFactory(new PropertyValueFactory<>("BranchNameByLocation"));
		PONumber.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderId"));
		POprice.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderprice"));
		PODate.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderDate"));
		EmpResponseNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseId"));
		EmpResponseName.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseName"));
		SupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
		SupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("SupplierphoneNumb"));

		PurchaseOrdersTable.setItems(PurchaseOrderslist);
		System.out.println(PurchaseOrderslist.isEmpty());
	


    	
    }
    
    
    @FXML
	private TextField listByBranchText;
    public void showpOByBranchName() throws SQLException{
    	PurchaseOrderslist.clear();
    	String BranchNamee =listByBranchText.getText();
    	pOByBranchName(BranchNamee);


    }
    
    
    
    public void pOByEmpName(String EmppNamee) throws SQLException{
    	String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and  e.EmployeeName=" + '"' + EmppNamee + '"' + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);
		System.out.println(rs.toString());


		while (rs.next()) {
			PurchaseOrderslist.add(new MakePurchaseOrder(rs.getString(1),Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
					rs.getString(4),Integer.parseInt(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8)));

			System.out.println(rs.getString(1) + " " + Integer.parseInt(rs.getString(2)) + " " + Integer.parseInt(rs.getString(3)) + " "
					+ rs.getString(4) + " " + Integer.parseInt(rs.getString(5)) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8));

		}
		System.out.println(PurchaseOrderslist.isEmpty());

		BranchName.setCellValueFactory(new PropertyValueFactory<>("BranchNameByLocation"));
		PONumber.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderId"));
		POprice.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderprice"));
		PODate.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderDate"));
		EmpResponseNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseId"));
		EmpResponseName.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseName"));
		SupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
		SupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("SupplierphoneNumb"));

		PurchaseOrdersTable.setItems(PurchaseOrderslist);
		System.out.println(PurchaseOrderslist.isEmpty());
	


    	
    }
    
    
    @FXML
	private TextField listByEmpText;
    public void showPOByEmpName() throws SQLException{
    	PurchaseOrderslist.clear();
    	String EmpNamee =listByEmpText.getText();
    	pOByEmpName(EmpNamee);
    }
    
    public void refereshPOButton() throws ClassNotFoundException, SQLException
	{
		System.out.println("refereshPOButton");

    	PurchaseOrderslist.clear();
		getPurchaseOrdersTable();

	}
	
    
    public int ListAllPOandItsTotalCostFromSupplierForBranch(String BranchNamee,String supplierNamee) throws SQLException{
    	String mystring = "select b.nameByLocation ,m.purchaseOrderId,o.purchaseOrderprice,o.purchaseOrderDate,m.EmployeeResponseId,e.EmployeeName,s.SupplierName,s.SupplierPhone from Branch b,Employees e,Suppliers s ,PurchaseOrder o ,makePurchaseOrder m where m.BranchId=b.BranchId and m.SupplierID=s.SupplierID and e.EmployeeId=m.EmployeeResponseId and m.purchaseOrderId=o.purchaseOrderId and s.SupplierName= '" + supplierNamee +"' and b.nameByLocation= '"+BranchNamee+"';";
    	Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);
		System.out.println(rs.toString());
		int Cost=0;


		while (rs.next()) {
			PurchaseOrderslist.add(new MakePurchaseOrder(rs.getString(1),Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
					rs.getString(4),Integer.parseInt(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8)));

			System.out.println(rs.getString(1) + " " + Integer.parseInt(rs.getString(2)) + " " + Integer.parseInt(rs.getString(3)) + " "
					+ rs.getString(4) + " " + Integer.parseInt(rs.getString(5)) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8));
			
			Cost = Cost+Integer.parseInt(rs.getString(3));
		}
		System.out.println(PurchaseOrderslist.isEmpty());

		BranchName.setCellValueFactory(new PropertyValueFactory<>("BranchNameByLocation"));
		PONumber.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderId"));
		POprice.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderprice"));
		PODate.setCellValueFactory(new PropertyValueFactory<>("purchaseOrderDate"));
		EmpResponseNumber.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseId"));
		EmpResponseName.setCellValueFactory(new PropertyValueFactory<>("EmployeeResponseName"));
		SupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
		SupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("SupplierphoneNumb"));

		PurchaseOrdersTable.setItems(PurchaseOrderslist);
		System.out.println(PurchaseOrderslist.isEmpty());
    	
	    	return Cost;
    	
    }
    
    
	
    @FXML
	private TextField TotalCostText;
    @FXML
	private TextField listSupplierText;
    @FXML
	private TextField listBySupplierText;
    public void showAllPOandItsTotalCostFromSupplierForBranch() throws SQLException{
    PurchaseOrderslist.clear();
    String sNmae=listSupplierText.getText();
    String BNmae=listBySupplierText.getText();	
    int cost=ListAllPOandItsTotalCostFromSupplierForBranch(BNmae,sNmae);
    TotalCostText.setText(""+cost);	
    }

    
    
    ///// Add purchas Order Tabs
    
    
    @FXML
    private TextField supEmailTextAddItem;

    @FXML
    private TextField supLocationTextAddItem;

    @FXML
    private TextField supNameTextAddItem;

    @FXML
    private TextField supPhoTextAddItem;
    
    @FXML
    private TextField empNumberTextAddItem;

    
    @FXML
    private TextField braNameTextAddItem;
    
    @FXML
    private TextField DateofPurchasOrderText;
    
    
    
    
    
    @FXML
    private TextField ItemType1; 
    
    @FXML
    private TextField ItemPrice1;
	
    @FXML
    private TextField ItemNmae1;
    
    @FXML
    private TextField ItemQuantity1;
    
    
	private ObservableList<AddItem> ItemAddlist1 = FXCollections.observableArrayList();
	private ObservableList<Suppliers> supplierslist = FXCollections.observableArrayList();


        
	@FXML
	public void insertItemToPO(ActionEvent event) throws ClassNotFoundException, SQLException {
	String IName=ItemNmae1.getText();
	int Iprice=Integer.parseInt(ItemPrice1.getText());
	int Iquantity=Integer.parseInt(ItemQuantity1.getText());
	String Itype =ItemType1.getText();
	ItemAddlist1.add(new AddItem(IName,Iprice,Iquantity,Itype));
	System.out.println(ItemAddlist1);
}
@FXML //insertPOInformation
public void insertPOInformation(ActionEvent event) throws ClassNotFoundException, SQLException  {
	String branchNmae=braNameTextAddItem.getText();
	String empResNumb=empNumberTextAddItem.getText();
	String supName=supNameTextAddItem.getText();
	String supPhone=supPhoTextAddItem.getText();
	String supLocation=supLocationTextAddItem.getText();
	String supEmail=supEmailTextAddItem.getText();
	String DatePO=DateofPurchasOrderText.getText();
	
	float TotalPriceForPO=0;
    System.out.println("in insertPOInformation");
	try {
		for (int i =0;i<ItemAddlist1.size();i++) {
			TotalPriceForPO=TotalPriceForPO+(ItemAddlist1.get(i).getItemPrice()*ItemAddlist1.get(i).getItemQuantity());
		}
		System.out.println("TotalPriceForPO:"+TotalPriceForPO);
		
		// insert to p o table
		String POTableString = "insert into purchaseOrder values(0,"+TotalPriceForPO+",'"+DatePO+"');";
		System.out.println("1");
		Statement stmtT = con.createStatement();
		System.out.println("2");
		stmtT.execute(POTableString);
		System.out.println("3");
		System.out.println(POTableString);
		
		//get p o id 
		String POTableString1 = "select purchaseOrderId from purchaseOrder where purchaseOrderprice="+TotalPriceForPO+"and purchaseOrderDate='"+DatePO+"';";
		Statement stmt1 = con.createStatement();
		System.out.println("4");
		ResultSet rs2 = stmt1.executeQuery(POTableString1);
		System.out.println("5");
		int poId=0;
		while (rs2.next()) {
			 poId =Integer.parseInt(rs2.getString(1));
		}	
		
		System.out.println(POTableString1);
		System.out.println(poId+" poId");
		
		
		
		// insert to item and p o line tables
		System.out.println("insert to item and p o line tables");
			int ItemId=0;
			float priceLine=0;
			String itemStringg;
			Statement stmt8 ;
			for (int i =0;i<ItemAddlist1.size();i++) {
				// insert Item
				itemStringg = "insert into Item values(0,'"+ItemAddlist1.get(i).getItemName()+"',"+ItemAddlist1.get(i).getItemPrice()+",'"+ItemAddlist1.get(i).getItemType()+"');";
				System.out.println("6");
				stmt8 = con.createStatement();
				System.out.println("7");
				stmt8.execute(itemStringg);
				System.out.println(itemStringg);
				
				//get Item ID
				String itemmString2 = "select ItemId from Item where ItemName='"+ItemAddlist1.get(i).getItemName()+"'and  ItemType='"+ItemAddlist1.get(i).getItemType()+"' and ItemPrice="+ItemAddlist1.get(i).getItemPrice()+";";
				Statement stmt9 = con.createStatement();
				ResultSet rs9 =stmt9.executeQuery(itemmString2);
				

				while (rs9.next()) {
					ItemId = Integer.parseInt(rs9.getString(1));
				}
				System.out.println(ItemId+" ItemId");
				
				//insert p o line
				
				priceLine=ItemAddlist1.get(i).getItemQuantity()*ItemAddlist1.get(i).getItemPrice();
				String polineString = "insert into purchaseOrderLine value("+poId+","+ItemId+","+ItemAddlist1.get(i).getItemQuantity()+","+priceLine+");";
				Statement stmt10 = con.createStatement();
				stmt10.execute(polineString);
				System.out.println(priceLine);

					
			}
			
			


		
		
		
		
		//check if supplier is exist and get his id
		System.out.println("check if supplier is exist and get his id");
		String SupplierString1 = "select SupplierID,SupplierName,SupplierPhone,SupplierEmail,SupplierLocation from suppliers where SupplierLocation='"+supLocation+" 'and  SupplierName='"+supName+" ' and SupplierPhone='"+supPhone+" 'and SupplierEmail='"+supEmail+"';";
		Statement stmt2 = con.createStatement();
		System.out.println("8");
		ResultSet rs3 = stmt2.executeQuery(SupplierString1);
		System.out.println("9");
		System.out.println(SupplierString1);

		
		while (rs3.next()) {
			supplierslist.add(new Suppliers(Integer.parseInt(rs3.getString(1)),rs3.getString(2),rs3.getString(3),
					rs3.getString(4),rs3.getString(5)));
		}	
		System.out.println(supplierslist);

		
		int supId=0;
		if(supplierslist.isEmpty())
		{// insert new supplier and get his ID
			
			//Insert new supplier
			String SUPString = "insert into Suppliers value(0,'"+supName+"','"+supPhone+"','"+supEmail+"','"+supLocation+"');";
			Statement stmt3 = con.createStatement();
			System.out.println("10");

			stmt3.execute(SUPString);
			System.out.println("11");

			System.out.println(SUPString);

			
			//get supplier ID
			String SUPString2 = "select SupplierID  from suppliers where SupplierLocation='"+supLocation+"'and  SupplierName='"+supName+"' and SupplierPhone='"+supPhone+"'and SupplierEmail='"+supEmail+"';";
			Statement stmt4 = con.createStatement();
			ResultSet rs5 =stmt4.executeQuery(SUPString2);
			while (rs5.next()) {
				supId = Integer.parseInt(rs5.getString(1));

			}
			System.out.println(SUPString2);
			System.out.println(" not Exist supId "+supId);


			
		}
		else {
		
			supId = Integer.parseInt(rs3.getString(1));//??
			System.out.println("Exist supId "+supId);


		}
		
		
		
		
		//get branch id
		int BranchhhId =0;
		String BraString = "select BranchId from Branch where nameByLocation='"+branchNmae+"';";
		Statement stmt5 = con.createStatement();
		ResultSet rs6 =stmt5.executeQuery(BraString);
		while (rs6.next()) {		
			BranchhhId = Integer.parseInt(rs6.getString(1));
             }
		System.out.println(BraString);
		System.out.println(BranchhhId+" BranchhhId");


		
		// insert line to MPO Table
		System.out.println("insert line to MPO Table");
		System.out.println(""+BranchhhId+","+poId+","+empResNumb+","+supId+"");


		String mpoString = "insert into makePurchaseOrder value("+BranchhhId+","+poId+","+empResNumb+","+supId+");";
		Statement stmt7 = con.createStatement();
		stmt7.execute(mpoString);
		System.out.println(mpoString);
		
		System.out.println("End");


		
	} catch (SQLException ex) {
    return;
    }
		
}

//Branch Tabe///////////////


	@FXML
	private TableView<Branch> BranchTable;

	@FXML
	private TableColumn<Branch, String> nameBYLocClm;

	@FXML
	private TableColumn<Branch, String> BranchPhoNum;

	@FXML
	private TableColumn<Branch, String> BranchEmail;

	@FXML
	private TableColumn<Branch, String> BranchManger;

	@FXML
	private TableColumn<Branch, Integer> NumOfEmp;
	@FXML
	private GridPane GridPaneBranch;

	@FXML
	private TextField NameByLocation;

	@FXML
	private TextField BranchID;

	@FXML
	private TextField BranchPhoneNum;

	@FXML
	private TextField BranchEmailtxt;

	@FXML
	private TextField BranchLoction;

	@FXML
	private TextField Mangerid;

	@FXML
	private TextField NumOfEmptxt;

	@FXML
	private TextField BranchExpenses;
	@FXML
	private TextField EmpSalaryes;
	@FXML
	private TableView<Employees> FloatingEmptable;

	@FXML
	private TableColumn<Employees, String> EmployeeName1;

	@FXML
	private TableColumn<Employees, String> EmployeeGender1;

	@FXML
	private TableColumn<Employees, String> EmployeeSalary1;

	@FXML
	private TableColumn<Employees, String> EmployeeDepartmentName1;

	@FXML
	private TableColumn<Employees, String> PhoneNumber1;
	@FXML
	private TextField INBranchID;

	private ObservableList<Branch> dataListBranch = FXCollections.observableArrayList();
	private ObservableList<Employees> dataListBranchEmp = FXCollections.observableArrayList();


	
	@FXML
	void getEmployeesByBranch(ActionEvent event) throws IOException, SQLException {
		BranchTable.setVisible(false);
		FloatingEmptable.setVisible(true);
		getEmployeeInBranch();
	}

	public void getEmployeeInBranch() throws SQLException {
		 refreshTableBrcEmp();
		String mystring = "select * from Employees where Employees.BranchWorkId=" + INBranchID.getText() + ";";
		System.out.println(mystring);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);

		while (rs.next()) {
			dataListBranchEmp.add(new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), Integer.parseInt(rs.getString(11))));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
					+ rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " "
					+ Integer.parseInt(rs.getString(11)));

		}

		EmployeeName1.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
		EmployeeGender1.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
		EmployeeSalary1.setCellValueFactory(new PropertyValueFactory<>("EmployeeSalary"));
		EmployeeDepartmentName1.setCellValueFactory(new PropertyValueFactory<>("EmployeeDepartmentName"));
		PhoneNumber1.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));
		
		FloatingEmptable.setItems(dataListBranchEmp);

	}

	public void getBranches() throws SQLException, ClassNotFoundException {

		String mystring = "select Branch.BranchId,Branch.location,Branch.nameByLocation,Branch.phoneNumber,Branch.Email,Employees.EmployeeName,Branch.numberOfEmployees from Branch,Employees,BranchManegers "
				+ "where BranchManegers.BranchId=Branch.BranchId and BranchManegers.managerEmployeeId=Employees.EmployeeId;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);

		while (rs.next()) {

			dataListBranch.add(new Branch(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(7)), rs.getString(6)));

			System.out.println(Integer.parseInt(rs.getString(1)) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4) + " " + rs.getString(5) + " " + Integer.parseInt(rs.getString(7)));

			System.out.println(dataListBranch.isEmpty());

			nameBYLocClm.setCellValueFactory(new PropertyValueFactory<>("nameByLocation"));
			BranchPhoNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
			BranchEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
			BranchManger.setCellValueFactory(new PropertyValueFactory<>("manger"));
			NumOfEmp.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployees"));
			BranchTable.setItems(dataListBranch);

		}

	}

	
	@FXML
	void ViewGridBranch(MouseEvent event) throws SQLException {
		GridPaneBranch.setVisible(true);
		Branch b = BranchTable.getSelectionModel().getSelectedItem();
		NameByLocation.setText(b.getNameByLocation());
		BranchID.setText(b.getBranchId() + "");
		BranchPhoneNum.setText(b.getPhoneNumber());
		BranchEmailtxt.setText(b.getEmail());
		BranchLoction.setText(b.getLocation());
		Employees e = EmployeesInformationObject(b.getManger());
		Mangerid.setText(e.getEmployeeId() + "");
		NumOfEmptxt.setText(b.getNumberOfEmployees() + "");
		
		//BranchExpenses.setText("make a quere");
		AllEmpSalary(b);
		BranchExpenses(b);
	}
	
	void BranchExpenses(Branch b) throws SQLException {
		
		//branch p o expenses
		String BraSalary = "select sum(o.purchaseOrderprice) from makePurchaseOrder m,purchaseOrder o where m.BranchId=" + b.getBranchId() + " and m.purchaseOrderId=o.purchaseOrderId;";

		System.out.println(BraSalary);
		Statement stmt20 = con.createStatement();
		ResultSet rs20 = stmt20.executeQuery(BraSalary);
		rs20.next();
	    int sum =Integer.parseInt(rs20.getString(1))+Integer.parseInt(EmpSalaryes.getText());
		
		BranchExpenses.setText(""+sum);

	}


	void AllEmpSalary(Branch b) throws SQLException {

		String mystring = "select sum(EmployeeSalary) from Employees where BranchWorkId=" + b.getBranchId() + "; ";
		System.out.println(mystring);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);
		rs.next();
		EmpSalaryes.setText(rs.getString(1));

	}

	
	public void refreshTableBrc() throws SQLException {
		dataListBranch.clear();
		String mystring = "select Branch.BranchId,Branch.location,Branch.nameByLocation,Branch.phoneNumber,Branch.Email,Employees.EmployeeName,Branch.numberOfEmployees from Branch,Employees,BranchManegers "
				+ "where BranchManegers.BranchId=Branch.BranchId and BranchManegers.managerEmployeeId=Employees.EmployeeId;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);

		while (rs.next()) {

			dataListBranch.add(new Branch(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(7)), rs.getString(6)));
			BranchTable.setItems(dataListBranch);

		}
	}
	public void refreshTableBrcEmp() throws SQLException {
		dataListBranchEmp.clear();

	}

	
	
	
	public Employees EmployeesInformationObject(String name) throws SQLException {
		String EmployeeInfo = "select * from Employees where EmployeeName =" + '"' + name + '"' + ";";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(EmployeeInfo);
		Employees e = null;
		while (rs.next()) {
			e = new Employees(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), Integer.parseInt(rs.getString(11)));

		}

		return e;

	}

	@FXML
	void ShowBranch(ActionEvent event) throws SQLException {
		BranchTable.setVisible(true);
		FloatingEmptable.setVisible(false);
		refreshTableBrc();
	}



////////////////////// Menu Hamedo




	
		
		private ObservableList<MENU> dataListMenu = FXCollections.observableArrayList();
		private ObservableList<Customer> dataList1 = FXCollections.observableArrayList();
		private ObservableList<CustomerOrder> dataList2 = FXCollections.observableArrayList();
		private ObservableList<OrderLine> dataList3 = FXCollections.observableArrayList();
		private ObservableList<MakeCustomerOrder> dataList4 = FXCollections.observableArrayList();

	    @FXML
	    private Button ADD;

	    @FXML
	    private AnchorPane AnchorCustomer;

	    @FXML
	    private TableView<MENU> MENUTabel;
	    
	    @FXML
	    private Button AllOrders;

	    @FXML
	    private Tab MENUTap;

	    @FXML
	    private TabPane MainTap;

	    @FXML
	    private Tab CustomerTab;

	    @FXML
	    private Button delete;

	    @FXML
	    private TableColumn<MENU, String> foodType;

	    @FXML
	    private TextField id;

	    @FXML
	    private Label idText;

	    @FXML
	    private TableColumn<MENU, String> itemID;

	    @FXML
	    private TableColumn<MENU, String> itemName;

	    @FXML
	    private TableColumn<MENU, String> itemPrice;

	    @FXML
	    private TextField name;

	    @FXML
	    private Label nameText;

	    @FXML
	    private TextField price;

	    @FXML
	    private Label pruceText;

	    @FXML
	    private TextField type;

	    @FXML
	    private Label typeText;
	    
	    @FXML
	    private TextField AllOrderfromE;

	    @FXML
	    private Button AllOrderfromEBtn;
	    
	    @FXML
	    private TableView<Customer> CustomerTabele;
	    
	    @FXML
	    private TableColumn<Customer, String> CustomerAddress;

	    @FXML
	    private TableColumn<Customer, String> CustomerID;

	    @FXML
	    private TableColumn<Customer, String> CustomerName;

	    @FXML
	    private TableColumn<Customer, String> CustomerPhone;
	    
	    @FXML
	    private TableColumn<Customer, String> OrderID;
	    
	    @FXML
	    private Button ShowAllCustomer;
	    
	    @FXML
	    private Button OutCustomers;
	    
	    @FXML
	    private Button InCustomers;
	    
	    @FXML
	    private TextField BranchNameText;
	    
	    @FXML
	    private Button CustomersInBranch;
	    
	    @FXML
	    private TableView<CustomerOrder> CustomerOrderTable;
	    
	    @FXML
	    private TableColumn<CustomerOrder, String> DeliveryMethod;
	    
	    @FXML
	    private TableColumn<CustomerOrder, String> OrderDate;

	    @FXML
	    private TableColumn<CustomerOrder, String> OrderPrice;
	    
	    @FXML
	    private Tab OrderTap;
	    
	    @FXML
	    private TableView<MakeCustomerOrder> MakeCustomerOrderTable;
	    
	    @FXML
	    private TableColumn<MakeCustomerOrder, String> EmployeeResponseID;
	    
	    @FXML
	    private TableColumn<OrderLine, String> ItemQantity3;
	    @FXML
	    private TableColumn<OrderLine, String> itemName3;
	    
	    @FXML
	    private TableView<OrderLine> OrderLineTable;
	    
	    @FXML
	    private TableColumn<OrderLine, String> priceLine3;
	    
	    @FXML
	    private TextField BranchToMax;

	    @FXML
	    private TextField BranchToTotal;
	    
	    @FXML
	    private Button ListTheMaxOrder;

	    @FXML
	    private Button ListTheTotalCost;
	    
	    @FXML
	    private TextField TotalCost;
	    
	 
	    
	    
	  

		private void getCustomerOrder() throws SQLException {
			// TODO Auto-generated method stub
	    	String mystring = "select * from CustomerOrder";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(mystring);
			while (rs.next()) {
				dataList2.add(new CustomerOrder(rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getString(4)));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));
			}
			System.out.println(dataList2.isEmpty());
			OrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
			OrderPrice.setCellValueFactory(new PropertyValueFactory<>("OrderPrice"));
			DeliveryMethod.setCellValueFactory(new PropertyValueFactory<>("DeliveryMethod"));
			CustomerOrderTable.setItems(dataList2);
			
		}



		
	    
	    private void getMENU() throws SQLException, ClassNotFoundException {
			// TODO Auto-generated method stub
	    	String mystring = "select * from MENU";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(mystring);
			while (rs.next()) {
				dataListMenu.add(new MENU(rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getString(4)));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));
			}
			System.out.println(" fe f"+dataListMenu.isEmpty());
			//itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
			itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
			itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
			foodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));
			System.out.println(" 8787");
			MENUTabel.setItems(dataListMenu);

			
			
		}
	    
	    private void getCustomer() throws SQLException {
	    	String mystring="select * from Customer";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
			while(rs.next()) {
				dataList1.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));			
			}
			System.out.println(dataList1.isEmpty());
			CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
			CustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
			CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
			CustomerTabele.setItems(dataList1);
	    	
	    }
	    
	   

	    @FXML
	    void deleteMenu(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	connectDB();
			MENU e = MENUTabel.getSelectionModel().getSelectedItem();

			String mystring = "delete from  MENU where itemID =" + '"' + e.getItemID() + '"' + ";";
			//System.out.println(mystring);
			System.out.println(mystring);
	//delete from  Suppliers where name ="rishonPizzaSupplyer";
			Statement stmt = con.createStatement();
			stmt.execute(mystring);

			refreshTableMenu();

	    }

	    @FXML
	    void insertMenu(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	connectDB();
			String mystring = "Insert into MENU  values(0," + '"'
					+ name.getText() + '"' + "," + '"' + price.getText() + '"' + "," + '"' + type.getText()
					+ '"' + ");";
			System.out.println(mystring);
	//ExecuteStatement("Insert into Suppliers  values("+Integer.parseInt(idtxt.getText())+","+nametxt.getText()+","+phonetxt.getText()+","+emailtxt.getText()+","+locationtxt.getText());
			Statement stmt = con.createStatement();
			stmt.execute(mystring);

			refreshTableMenu();

	    }
	    
	    private void refreshTableMenu() {
			// TODO Auto-generated method stub
			try {
				dataListMenu.clear();

				String mystring = "select * from MENU";

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(mystring);
				while (rs.next()) {
					dataListMenu.add(new MENU(rs.getString(1), rs.getString(2),
							rs.getString(3), rs.getString(4)));

					// one tuple at a time
					MENUTabel.setItems(dataListMenu);
				}

			} catch (SQLException ex) {
				// TODO: handle exception
			}
			
		}
	    
	    @FXML
	    void ShowAllCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	dataList1.clear();
			getCustomer();

	    }
	    
	    @FXML
	    void OutCustomers(ActionEvent event) throws SQLException {
	    	dataList1.clear();
	    	String mystring="select * from Customer where CustomerAddress is not NULL";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
			while(rs.next()) {
				dataList1.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));			
			}
			System.out.println(dataList1.isEmpty());
			CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
			CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
			CustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
			CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
			CustomerTabele.setItems(dataList1);

	    }
	    
	    @FXML
	    void InCustomers(ActionEvent event) throws SQLException {
	    	dataList1.clear();
	    	String mystring="select * from Customer where CustomerAddress is NULL";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
			while(rs.next()) {
				dataList1.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));			
			}
			System.out.println(dataList1.isEmpty());
			CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
			CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
			CustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
			CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
			CustomerTabele.setItems(dataList1);

	    }
	    
	    @FXML
	    void CustomersInBranch(ActionEvent event) throws SQLException {
	    	dataList1.clear();
	    	String TheBranch,Cid = null,Eid=null;
	    	TheBranch=BranchNameText.getText();
	    	String mystring="Select DISTINCT(EmployeeResponseID)  from Branch b,Employees e ,MakeCustomerOrder m where e.EmployeeID=m.EmployeeResponseID&&b.BranchId=e.BranchWorkId&& b.nameByLocation="+'"'+TheBranch+'"'+";";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
	    	while(rs.next()) {
	    		Eid=rs.getString(1);
	    		String mystring2="select CustomerID from MakeCustomerOrder where EmployeeResponseID="+'"'+Eid+'"'+";";
	        	Statement stmt2 = con.createStatement();
	        	ResultSet rs2 = stmt2.executeQuery(mystring2);
	        	
	    	while(rs2.next()) {
	    		Cid=rs2.getString(1);
	    	
	    	String mystring1="select * from Customer where CustomerID="+'"'+Cid+'"'+";";
	    	Statement stmt1 = con.createStatement();
	    	ResultSet rs1 = stmt1.executeQuery(mystring1);
			while(rs1.next()) {
				dataList1.add(new Customer(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4)));
				System.out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " "
						+ rs1.getString(4));			
			}
			}
	    	}
			System.out.println(dataList1.isEmpty());
			CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
			CustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
			CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
			CustomerTabele.setItems(dataList1);

	    }
	    
	    @FXML
	    void ViewOrderLine(MouseEvent event) throws SQLException {
	    	dataList3.clear();
	    	CustomerOrder Co =CustomerOrderTable.getSelectionModel().getSelectedItem();
	    	String Coid=Co.getOrderID();
	    	System.out.println(Coid+"rrrrrrrrr");

	        String mystring = "select m.ItemName,l.ItemQuantity,l.priceLine from  OrderLine l,Menu m where l.itemID=m.ItemId and l.OrderID="+Coid+";";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(mystring);


			while (rs.next()) {
				dataList3.add(new OrderLine(rs.getString(1),rs.getString(2),
						rs.getString(3)));

				System.out.println(rs.getString(1) + " " 
						+ rs.getString(2) + " " + rs.getString(3));

			}
			System.out.println(dataList3.isEmpty());

			itemName3.setCellValueFactory(new PropertyValueFactory<>("ItemNmae"));
			ItemQantity3.setCellValueFactory(new PropertyValueFactory<>("ItemQuantity"));
			priceLine3.setCellValueFactory(new PropertyValueFactory<>("priceLine"));
			OrderLineTable.setItems(dataList3);

	    }

	    @FXML
	    void ListTheMaxOrder(ActionEvent event) throws SQLException {
	    	dataList2.clear();
	    	String returned=null;
	    	String mystring="Select max(OrderPrice) as MOP  from CustomerOrder C,MakeCustomerOrder M,Employees e ,Branch b where b.nameByLocation="+'"'+BranchToMax.getText()+'"'+"&&b.BranchId=e.BranchWorkId&&M.EmployeeResponseID=e.EmployeeID&&M.OrderID=C.OrderID;";
	    	PreparedStatement stmt = con.prepareStatement(mystring);
	    	ResultSet rs= stmt.executeQuery();
	    	if(rs.next()) {
				System.out.println("  lllllllllll");
				returned=rs.getString("MOP");
			    System.out.println("  lllllllllll");
			}
	    	String mystring1 = "select * from CustomerOrder where OrderPrice="+'"'+returned+'"'+";";
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(mystring1);
			while (rs1.next()) {
				dataList2.add(new CustomerOrder(rs1.getString(1), rs1.getString(2),rs1.getString(3),
						rs1.getString(4)));
				System.out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " "
						+ rs1.getString(4));
			}
			System.out.println(dataList2.isEmpty());
			OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
			OrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
			OrderPrice.setCellValueFactory(new PropertyValueFactory<>("OrderPrice"));
			DeliveryMethod.setCellValueFactory(new PropertyValueFactory<>("DeliveryMethod"));
			CustomerOrderTable.setItems(dataList2);
	    	

	    }

	    @FXML
	    void ListTheTotalCost(ActionEvent event) throws SQLException {
	    	dataList2.clear();
	    	int Total=0;
	    	String TheBranch;
	    	TheBranch=BranchToTotal.getText();
	    	String mystring="Select EmployeeId  from Branch b,Employees e where nameByLocation="+'"'+TheBranch+'"'+"&& b.BranchId=e.BranchWorkId ;";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
			while(rs.next()) {
				String mystring1="Select OrderID  from MakeCustomerOrder where EmployeeResponseID="+'"'+rs.getString(1)+'"'+";";
		    	Statement stmt1 = con.createStatement();
		    	ResultSet rs1 = stmt1.executeQuery(mystring1);
		    	while(rs1.next()) {
		    		String mystring2="Select *  from CustomerOrder where OrderID="+'"'+rs1.getString(1)+'"'+";";
		        	Statement stmt2 = con.createStatement();
		        	ResultSet rs2 = stmt2.executeQuery(mystring2);
		        	while(rs2.next()) {
		        		dataList2.add(new CustomerOrder(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4)));
				        /*System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3) + " "
						+ rs2.getString(4)+" "+rs2.getString(5));*/
				        Total=Total+Integer.parseInt(rs2.getString(3));
		        	}	
		    	}				
			}
			System.out.println(dataList2.isEmpty());
			OrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
			OrderPrice.setCellValueFactory(new PropertyValueFactory<>("OrderPrice"));
			DeliveryMethod.setCellValueFactory(new PropertyValueFactory<>("DeliveryMethod"));
			CustomerOrderTable.setItems(dataList2);
			TotalCost.setText(Integer.toString(Total));

	    }
	    
	    @FXML
	    void AllOrderfromE(ActionEvent event) throws SQLException {
	    	dataList2.clear();
	    	String Ename=AllOrderfromE.getText();
	    	String mystring="select EmployeeID from Employees where EmployeeName="+'"'+Ename+'"'+";";
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(mystring);
	    	if(rs.next()) {
	    		String mystring1="select OrderID from MakeCustomerOrder where EmployeeResponseID="+rs.getInt(1)+";";
	    		Statement stmt1 = con.createStatement();
	        	ResultSet rs1 = stmt1.executeQuery(mystring1);
	        	while(rs1.next()) {
	        		String mystring2="Select *  from CustomerOrder where OrderID="+'"'+rs1.getString(1)+'"'+";";
		        	Statement stmt2 = con.createStatement();
		        	ResultSet rs2 = stmt2.executeQuery(mystring2);
		        	while(rs2.next()) {
		        		dataList2.add(new CustomerOrder(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4)));
				        /*System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3) + " "
						+ rs2.getString(4)+" "+rs2.getString(5));*/
		        	}
	        	}	
	    	}
	    	System.out.println(dataList2.isEmpty());
			OrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
			OrderPrice.setCellValueFactory(new PropertyValueFactory<>("OrderPrice"));
			DeliveryMethod.setCellValueFactory(new PropertyValueFactory<>("DeliveryMethod"));
			CustomerOrderTable.setItems(dataList2);

	    }
	    
	    @FXML
	    void ShowAllOrders(ActionEvent event) throws SQLException {
	    	dataList2.clear();
			getCustomerOrder();

	    }


	}
	
	
	
	
///////////////////////	
	

