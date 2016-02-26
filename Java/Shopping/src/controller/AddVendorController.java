package controller;

import dao.VendorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Vendor;

public class AddVendorController {
	@FXML private TextField vendor_name,vendor_address,vendor_email,vendor_contact;
	@FXML private Button submit;


	@FXML
    public void initialize() {}

    @FXML public void submitOnClick(ActionEvent event) throws Exception {
    	System.out.println("Button Pressed");
    	Vendor vendor = new Vendor();

      	vendor.setVendor_name(vendor_name.getText());
      	vendor.setVendor_address(vendor_address.getText());
      	vendor.setVendor_phone(Long.parseLong(vendor_contact.getText()));
      	vendor.setVendor_email(vendor_email.getText());
        VendorDao vendordao = new VendorDao();
        vendordao.create(vendor);
        System.out.println("Dao Created");
    }

}
