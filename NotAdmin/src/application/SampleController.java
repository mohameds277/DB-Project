package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SampleController {
	private static final String SQL = null;
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "sam360396";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Villa_pizza2";
	private Connection con;
	private ObservableList<Buy> dataList = FXCollections.observableArrayList();
	ObservableList<String>methodList= FXCollections.observableArrayList("in","out");
	String [][]Or=new String[20][5];
	
	@FXML
	private ResourceBundle resources;
	
    @FXML
	private TextArea TheOrderAtAll;
	 
    @FXML
    private Tab printTap; 

	@FXML
	private URL location;

    @FXML
    private Button AddBtn;

    @FXML
    private TableView<Buy> BuyTabele;

    @FXML
    private TextField CustomerAddress;

    @FXML
    private TextField CustomerName;

    @FXML
    private AnchorPane BuyAnchor;
    
    @FXML
    private TextField CustomerPhone;

    @FXML
    private Button DeleteBtn;

    @FXML
    private ChoiceBox<String> DeliveryMethod;

    @FXML
    private Button Done;

    @FXML
    private TextField ItemQuantity;

    @FXML
    private TabPane MainTab;

    @FXML
    private TextField OrderDate;

    @FXML
    private TextField OrderPrice;

    @FXML
    private Tab buyTab;

    @FXML
    private TableColumn<Buy, String> foodType;

    @FXML
    private TableColumn<Buy, String> itemID;

    @FXML
    private TableColumn<Buy, String> itemName;

    @FXML
    private TableColumn<Buy, String> itemPrice;
    
    
    @FXML
	void initialize() throws ClassNotFoundException, SQLException {
		connectDB();
		getBuy();
		BuyTabele.setEditable(true);
		edit();
		DeliveryMethod.setItems(methodList);
		
	}

    private void edit() {
		// TODO Auto-generated method stub
    	itemID.setCellFactory(TextFieldTableCell.forTableColumn());
    	itemID.setOnEditCommit(e -> {
			e.getTableView().getItems().get(BuyTabele.getSelectionModel().getSelectedIndex())
					.setItemID(e.getNewValue());
			try {
				update(BuyTabele.getSelectionModel().getSelectedItem());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		itemName.setCellFactory(TextFieldTableCell.forTableColumn());
		itemName.setOnEditCommit(e -> {
			e.getTableView().getItems().get(BuyTabele.getSelectionModel().getSelectedIndex())
					.setItemName(e.getNewValue());
			try {
				update(BuyTabele.getSelectionModel().getSelectedItem());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		itemPrice.setCellFactory(TextFieldTableCell.forTableColumn());
		itemPrice.setOnEditCommit(e -> {
			e.getTableView().getItems().get(BuyTabele.getSelectionModel().getSelectedIndex())
					.setItemPrice(e.getNewValue());
			try {
				update(BuyTabele.getSelectionModel().getSelectedItem());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		foodType.setCellFactory(TextFieldTableCell.forTableColumn());
		foodType.setOnEditCommit(e -> {
			e.getTableView().getItems().get(BuyTabele.getSelectionModel().getSelectedIndex())
					.setFoodType(e.getNewValue());
			try {
				update(BuyTabele.getSelectionModel().getSelectedItem());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});	
		
	}

	private void update(Buy selectedItem) throws SQLException{
		// TODO Auto-generated method stub
		ExecuteStatement("update  MENU set itemID = " + '"' + selectedItem.getItemID() + '"' + ","
				+ "itemName = " + '"' + selectedItem.getItemName() + '"' + ","
				+ "itemPrice = " + '"' + selectedItem.getItemPrice() + '"' + "," + "foodType = " + '"'
				+ selectedItem.getFoodType() + '"' + ";");
		
	}

	private void ExecuteStatement(String string) throws SQLException{
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}
		
	}

	private void getBuy() throws SQLException {
		// TODO Auto-generated method stub
		String mystring = "select * from MENU";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(mystring);

		while (rs.next()) {
			dataList.add(new Buy(rs.getString(1), rs.getString(2),rs.getString(3),
					rs.getString(4)));

			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
					+ rs.getString(4));

		}
		System.out.println(dataList.isEmpty());

		itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		foodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));

		BuyTabele.setItems(dataList);
		OrderPrice.setText("0");
		//System.out.println(OrderPrice.getText());
		
	}

	private void connectDB() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);
		
	}

	@FXML
    void ADDToOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
		connectDB();
		Buy e = BuyTabele.getSelectionModel().getSelectedItem();
		int i,price=0;
		for(i=0;i<20;i++) {
			System.out.println(i);
			if(Or[i][0]==null||Or[i][0]=="no") {
			Or[i][0]=e.getItemID();
			Or[i][1]=e.getItemName();
			Or[i][2]=e.getItemPrice();
			Or[i][3]=e.getFoodType();
			Or[i][4]=ItemQuantity.getText();
			
			break;
			}
			//System.out.println(itemID.getCellData(e));
			
		}
		for(i=0;i<20;i++) {
			if(Or[i][0]==null||Or[i][0]=="no") {
				break;}
			System.out.println(i);
			System.out.println(Or[i][0]);
			System.out.println(Or[i][1]);
			System.out.println(Or[i][2]);
			System.out.println(Or[i][3]);
			System.out.println(Or[i][4]);
			price=price+(Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]));
			
		}
		OrderPrice.setText(Integer.toString(price));
		
    }

    @FXML
    void DeleteFromOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
    	connectDB();
		Buy e = BuyTabele.getSelectionModel().getSelectedItem();
		int i,price;
		price=Integer.parseInt(OrderPrice.getText());
		for(i=0;i<20;i++) {
			System.out.println(i);
			if(Or[i][0]==e.getItemID()) {
				price=price-(Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]));
				while(i<19) {
					if(Or[i+1][0]==null&& Or[i][0]!=null || Or[i][0]=="no"&& Or[i][0]!="no") {
						Or[i][0]=null;
						Or[i][1]=null;
						Or[i][2]=null;
						Or[i][3]=null;
						Or[i][4]=null;
						break;}
					else {
					Or[i][0]=Or[i+1][0];
					Or[i][1]=Or[i+1][1];
					Or[i][2]=Or[i+1][2];
					Or[i][3]=Or[i+1][3];
					Or[i][4]=Or[i+1][4];
					i++;}
				}
			break;
			}
			//System.out.println(itemID.getCellData(e));
			
		}
		for(i=0;i<20;i++) {
			if(Or[i][0]==null||Or[i][0]=="no") {
				break;}
			System.out.println(i);
			System.out.println(Or[i][0]);
			System.out.println(Or[i][1]);
			System.out.println(Or[i][2]);
			System.out.println(Or[i][3]);
			System.out.println(Or[i][4]);
			//price=price-(Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]));
			
		}
		OrderPrice.setText(Integer.toString(price));

    }

    @FXML
    void Finish(ActionEvent event) throws ClassNotFoundException, SQLException {
    	connectDB();
    	TheOrderAtAll.setText("");
    	int returned = 0,returned1 = 0,returned2 = 0,returned3=0,f;
    	String mystring = "Insert into CustomerOrder  values("+ null +","+'"'+  OrderDate.getText() +'"'+  "," + '"'
				+ OrderPrice.getText() + '"' + "," + '"' +DeliveryMethod.getValue() + '"' + ");";
    	Statement stmt = con.createStatement();
		stmt.execute(mystring);
		
		String mystring1="select max(OrderID) as max_id from CustomerOrder;";
		System.out.println("  lllllllllll");
		PreparedStatement stmt1 = con.prepareStatement(mystring1);
		System.out.println("  lllllllllll");
		ResultSet rs= stmt1.executeQuery();
		System.out.println("  lllllllllll");
		if(rs.next()) {
			System.out.println("  lllllllllll");
			returned=rs.getInt("max_id");
		    System.out.println("  lllllllllll");
		}
		
			System.out.println(Integer.toString(returned)+"  lllllllllll");
		
		for(int i=0;i<20;i++) {
			if(Or[i][0]==null||Or[i][0]=="no") {
				break;}
			f=(Integer.parseInt(Or[i][2]))*(Integer.parseInt(Or[i][4]));
			String mystring2 = "Insert into OrderLine  values("+ Integer.toString(returned) +  "," + '"'
					+ Or[i][4] + '"' + "," + '"' +Or[i][0] + '"' +","+'"'+Integer.toString(f)+'"'+ ");";
	    	Statement stmt2 = con.createStatement();
			stmt2.execute(mystring2);
			System.out.println(i);
			System.out.println(Or[i][0]);
			System.out.println(Or[i][1]);
			System.out.println(Or[i][2]);
			System.out.println(Or[i][3]);
			System.out.println(Or[i][4]);
			//price=price+(Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]));
			
		}
		if(DeliveryMethod.getValue()=="in") {
		String mystring3 = "Insert into Customer values("+ null  + "," + '"'
				+ CustomerName.getText() + '"' + "," + '"' + CustomerPhone.getText() + '"' + "," + null
				 + ");";
    	Statement stmt3 = con.createStatement();
		stmt3.execute(mystring3);
		}
		else {
			String mystring3 = "Insert into Customer values("+ null +  "," + '"'
					+ CustomerName.getText() + '"' + "," + '"' +CustomerPhone.getText() + '"' +","+'"'+CustomerAddress.getText()+'"'+");";
	    	Statement stmt3 = con.createStatement();
			stmt3.execute(mystring3);
			
		}
		
		String mystring4="select EmployeeId as Eid from Employees where EmployeePhone =" +'"' + HomeController.Ph +'"' + ";";;
		System.out.println("  lllllllllll");
		PreparedStatement stmt4 = con.prepareStatement(mystring4);
		System.out.println("  lllllllllll");
		ResultSet rs1= stmt4.executeQuery();
		System.out.println("  lllllllllll");
		if(rs1.next()) {
			System.out.println("  lllllllllll");
			returned1=rs1.getInt("Eid");
		    System.out.println("  lllllllllll");
		}
		
		String mystring2="select max(CustomerID) as maxCID from Customer;";
		System.out.println("  lllllllllll");
		PreparedStatement stmt2 = con.prepareStatement(mystring2);
		System.out.println("  lllllllllll");
		ResultSet rs2= stmt2.executeQuery();
		System.out.println("  lllllllllll");
		if(rs2.next()) {
			System.out.println("  lllllllllll");
			returned3 = rs2.getInt("maxCID");
		    System.out.println("  lllllllllll");
		}
		
		
		
		 System.out.println(Integer.toString(returned1)+Integer.toString(returned)+Integer.toString(returned2)+"hhhhhhh");
		String mystring6 = "Insert into MakeCustomerOrder value("+returned1+","+returned+","+returned3+");";
    	Statement stmt6 = con.createStatement();
    	System.out.println(mystring6);
		stmt6.execute(mystring6);
		System.out.println("ex");
		
		StringBuilder fieldCont =new StringBuilder("");
		for(int i=0;i<20;i++) {
			if(Or[i][0]==null||Or[i][0]=="no") {
				break;}
			int P=0;
			P=Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]);
			fieldCont.append("\n"+Or[i][1]+"...."+Or[i][2]+"...."+Or[i][4]+"....."+P+"\n");
		}
		fieldCont.append("\nTotal: "+OrderPrice.getText());
			
	   /* for(int i=0;i<20;i++) {
			if(Or[i][0]==null||Or[i][0]=="no") {
				break;}
			int P=0;
			P=Integer.parseInt(Or[i][2])*Integer.parseInt(Or[i][4]);
			TheOrderAtAll.setText("\n\n\n\n"+Or[i][1]+"..."+Or[i][2]+"..."+Or[i][4]+"..."+P+"\n");
			System.out.println(i);
			System.out.println(Or[i][1]);
			System.out.println(Or[i][2]);
			System.out.println(Or[i][4]);
		}*/
    	TheOrderAtAll.setText("name/price/quantity/priceLine"+"\n\n\n"+fieldCont.toString());
    	fieldCont.setLength(0);
		
    	System.out.println(DeliveryMethod.getValue());
    	System.out.println(CustomerName.getText());
    	System.out.println(CustomerPhone.getText());
    	System.out.println(CustomerAddress.getText());
    	System.out.println(OrderDate.getText());
    	System.out.println(OrderPrice.getText());
    	DeliveryMethod.setValue("");
    	CustomerName.setText("");
    	CustomerPhone.setText("");
    	CustomerAddress.setText("");
    	OrderDate.setText("");
    	OrderPrice.setText("0");
    	ItemQuantity.setText("");
    	int i;
        //Arrays.fill(Or, "no");
    	
    	
    	
    	for(i=0;i<20;i++) {
			Or[i][0]="no";
			Or[i][1]="no";
			Or[i][2]="no";
			Or[i][3]="no";
			Or[i][4]="no";
			
		}
    }

   

}
