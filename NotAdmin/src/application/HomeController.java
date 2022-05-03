package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "sam360396";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Villa_pizza2";
	private Connection con;
	
	public static String Ph;

    @FXML
    private Button Enter;

    @FXML
    private AnchorPane HomePane;

    @FXML
    private  TextField Name;

    @FXML
    private  TextField Number;
    
    

    @FXML
    void enterTo(ActionEvent event) throws IOException, SQLException {
    	Ph=Number.getText();
    	String returned = null;
    	System.out.println("  lllllllllll");
    	System.out.println( Number.getText());
    	System.out.println("  lllllllllll");
    	String mystring= "Select EmployeeName as CheckE from Employees where EmployeePhone = " +'"' + Number.getText() +'"' + ";";
    	PreparedStatement stmt = con.prepareStatement(mystring);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			System.out.println("  lllllllllll");
			returned=rs.getString("CheckE");
		    System.out.println("  lllllllllll");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "This employee not found Or the phone is incorrect");
		}
    	System.out.println(stmt + "2" + rs);
    	if (!(Name.getText().isEmpty()) && !(Number.getText().isEmpty()) && returned.equals(Name.getText()) ) {

			TabPane tableViewParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// if(tableViewScene.) System.out.println("NULLLL");

			window.setScene(tableViewScene);
			window.show();

		} else {
			JOptionPane.showMessageDialog(null, "This Phone is correct but the employee name is incorrect");
		}

    }
    
    @FXML
	void initialize() throws ClassNotFoundException, SQLException {
		connectDB();
		assert Name != null
				: "fx:id=\"UserNametxt\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";
		assert Number != null
				: "fx:id=\"Passwordtxt\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";
		assert Enter != null : "fx:id=\"lOginBtn\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";

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

}
