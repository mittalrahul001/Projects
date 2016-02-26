package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Customer;

public class AdminController {

	@FXML private Button addProduct;
	@FXML private Button addVendor;

	private MainApp MainApp;

    @FXML
    public void initialize() {
    }

    @FXML public void addProductOnClick(ActionEvent event) throws Exception
    {
    	//MainApp.callAddProductView();
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML public void addVendorOnClick(ActionEvent event) throws Exception
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddVendor.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
