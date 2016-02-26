package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vendor {

	private final StringProperty vendor_name, vendor_address, vendor_email;
	private int vendor_id;
	private long vendor_phone;

	public Vendor() {
		this( 0,null, null, null, 0);
	}

	public Vendor(int vendor_id,String vendor_name, String vendor_address, String vendor_email,int vendor_phone) {
		this.vendor_id 	    = vendor_id;
		this.vendor_name    = new SimpleStringProperty(vendor_name);
		this.vendor_address = new SimpleStringProperty(vendor_address);
		this.vendor_email   = new SimpleStringProperty(vendor_email);
		this.vendor_phone   = vendor_phone;
	}


	public final StringProperty vendor_nameProperty() {return this.vendor_name;	}
	public final StringProperty vendor_addressProperty() {return this.vendor_address;}
	public final StringProperty vendor_emailProperty() {return this.vendor_email;}

	public int getVendor_id() { return vendor_id; }
	public final java.lang.String getVendor_name() {return this.vendor_nameProperty().get();}
	public final java.lang.String getVendor_address() {return this.vendor_addressProperty().get();}
	public final java.lang.String getVendor_email() {return this.vendor_emailProperty().get();}
	public long getVendor_Phone() { return vendor_phone; }

	public void setVendor_id(int vendor_id) {this.vendor_id = vendor_id;}
	public final void setVendor_name(final java.lang.String vendor_name) {this.vendor_nameProperty().set(vendor_name);}
	public final void setVendor_address(final java.lang.String vendor_address) {this.vendor_addressProperty().set(vendor_address);}
	public final void setVendor_email(final java.lang.String vendor_email) {this.vendor_emailProperty().set(vendor_email);}
	public void setVendor_phone(long l) {this.vendor_phone = l;}








}
