package controller;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

public class LoginController {

	@FXML private TextField email;
	@FXML private Button loginButton;
    @FXML private PasswordField passwordNode;
    @FXML private Label information;

    private MainApp MainApp;
    private Stage primaryStage;

    @FXML
    public void initialize() {
    }

    @FXML public void loginAction (ActionEvent event) throws Exception {

    	String email_id  = email.getText();
    	System.out.println(email_id);
    	// Stores the entered text from the passwordField
    	String password = passwordNode.getText();
    	System.out.println(password);

    	// Stores the selected customer from the comboBox
    	CustomerDao customerdao = new CustomerDao();
    	Customer customer = new Customer();
    	customer = customerdao.read(email_id);

    	// If no customer is selected, a notification message appears
    	if (customer != null) {
    		String cust_password = customer.getPassword();

    		System.out.println(cust_password);
    		System.out.println(password);

    		// If no password is entered or if it is incorrect, a notification message appears

    		if(email_id.equals("admin") && password.equals("admin"))
    		{
    			MainApp.callAdminView();
    		}
    		else if (cust_password.equalsIgnoreCase(password)) {
    			//System.out.println("password match");
        		MainApp.callMainView(customer);
        	}
            else {
                information.setText("Incorrect password!");
            }
    	} else {
    		information.setText("No customer selected!");
    	}

    }
    // Called when the Cancel button is pressed. It closes the stage and the program
    @FXML public void handleCancel() {
    	primaryStage.close();
    }
 // Called when the 'Create New Account' label is pressed. 'Add Customer' view gets opened on the same stage.
    @FXML public void createNewAccount() {
    	MainApp.addCustomer(null);
    }
    // Creates a reference to the primaryStage
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    // Creates a reference to the MainApp, receives the customerList and populates the comboBox with the customers
    public void setApp(MainApp mainApp) {
    	this.MainApp = mainApp;
    }

}
