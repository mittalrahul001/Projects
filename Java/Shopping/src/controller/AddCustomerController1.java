package controller;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Customer;

public class AddCustomerController1 {

	@FXML private TextField firstNameField, lastNameField, emailAddressField, passwordField, streetField, cityField;
	@FXML private Label passwordLabel, addCustomerLabel;
	@FXML private Button cancel,okay;
	@FXML private Label errorMessage;

	private MainApp mainApp;
	private Customer loggedInCustomer;

	@FXML
    public void initialize() {}

	  public void setMainApp(MainApp mainApp, Customer customer) {
	        this.mainApp = mainApp;
	        loggedInCustomer = customer;
	        // show / hide nodes if a customer is logged in
	        if (loggedInCustomer != null) {
	        	addCustomerLabel.setText("Edit Details");
	        	passwordField.setVisible(false);
	        	passwordLabel.setVisible(false);
	        }
	    }

	@FXML public void handleOk(ActionEvent event) throws Exception {
		// checks if it is an existing customer...
			System.out.println("Button Pressed");
			Customer loggedInCustomer = new Customer();
			loggedInCustomer.setEmailAddress(emailAddressField.getText());
			loggedInCustomer.setFirstName(firstNameField.getText());
			loggedInCustomer.setLastName(lastNameField.getText());
			loggedInCustomer.setEmailAddress(emailAddressField.getText());
			loggedInCustomer.setStreet(streetField.getText());
			loggedInCustomer.setCity(cityField.getText());
			loggedInCustomer.setPassword(passwordField.getText());

			CustomerDao customerdao = new CustomerDao();
			customerdao.create(loggedInCustomer);
			System.out.println("Dao Created");
		// ... or a new customer
}
	@FXML public void handleCancel(ActionEvent event) throws Exception {
		if (loggedInCustomer != null) {
    		mainApp.showCustomer(loggedInCustomer);
    	} else {
    		mainApp.showLoginView();
    	}
	}
}
