package application;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.Node;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234db";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Villa_pizza";
	private Connection con;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField UserNametxt;

	@FXML
	private PasswordField Passwordtxt;

	@FXML
	private Button lOginBtn;
	// ######################WelcomeScreen###################//
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
	private TableColumn<Employees, Float> EmployeeSalary;

	@FXML
	private TableColumn<Employees, String> EmployeeDepartmentName;

	@FXML
	private TableColumn<Employees, String> PhoneNumber;

	@FXML
	private GridPane GridPaneEmp;

	@FXML

	void GiveAccess(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

		if (UserNametxt.getText().equals("admin") && Passwordtxt.getText().equals("1234")) {

			TabPane tableViewParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// if(tableViewScene.) System.out.println("NULLLL");

			window.setScene(tableViewScene);
			window.show();

		} else {
			JOptionPane.showMessageDialog(null, "wrong password or username");
		}
	}

	@FXML
	void initialize() throws ClassNotFoundException, SQLException {
		connectDB();
Dawaran(c1, true, 360, 10);
Dawaran(c2, true, 180, 18);
Dawaran(c3, true, 145, 24);
Dawaran(c4, true, 180, 10);
Dawaran(c5, true, 145, 18);
Dawaran(c6, true, 360, 24);
Dawaran(c7, true, 360, 10);
Dawaran(c8, true, 180, 18);
Dawaran(c9, true, 145, 24);

		
		
		
		
		
		
		assert UserNametxt != null
				: "fx:id=\"UserNametxt\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";
		assert Passwordtxt != null
				: "fx:id=\"Passwordtxt\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";
		assert lOginBtn != null : "fx:id=\"lOginBtn\" was not injected: check your FXML file 'WelcomeScreen.fxml'.";

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

	@FXML
	private Ellipse c1;

	@FXML
	private Ellipse c2;

	@FXML
	private Ellipse c3;

	@FXML
	private Ellipse c4;

	@FXML
	private Ellipse c5;

	@FXML
	private Ellipse c6;

	@FXML
	private Ellipse c7;

	@FXML
	private Ellipse c8;

	@FXML
	private Ellipse c9;

	public void Dawaran(Ellipse e, boolean rev, int angle, int dur) {

		RotateTransition rt = new RotateTransition(Duration.seconds(dur),e);
		rt.setAutoReverse(rev);
		rt.setByAngle(angle);
		rt.setDelay(Duration.seconds(0));
		rt.setRate(3);
		rt.setCycleCount(18);
		rt.play();
	}

}