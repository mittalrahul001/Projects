package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class Product {

	private int product_id;
	private final StringProperty name;
	private final IntegerProperty price;
	private final StringProperty category;
	private int product_isbn;
	private String product_author;
	private String product_publisher;
	private int product_quantity;
	private int product_volume;
	private String product_artist;
	private StringProperty description;
	private int vendor_id;
	private int rating;
	private ImageView image;

	public Product()
	{
		this(0,null,0,null,0,null,null,0,0,null,null,0,0,null);
	}
	// Initialisation
	public Product(int product_id,String name, int product_price,String category, int product_isbn,String product_author,String product_publisher,int product_quantity, int product_volume, String product_artist, String description, int vendor_id,int rating, ImageView image) {
		this.product_id = product_id;
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleIntegerProperty(product_price);
		this.category = new SimpleStringProperty(category);
		this.product_isbn = product_isbn;
		this.product_author = product_author;
		this.product_publisher = product_publisher;
		this.product_quantity = product_quantity;
		this.product_volume = product_volume;
		this.product_artist = product_artist;
		this.description = new SimpleStringProperty(description);
		this.vendor_id = vendor_id;
		this.rating = rating;
		this.image = image;

	}
	// getter
	public StringProperty nameProperty() { return name;}
	public IntegerProperty priceProperty() {
		return price;}
	public StringProperty categoryProperty() { return category;}

	public String getname() { return name.get(); }
	public int getid() {return product_id; }
	public String getName() { return name.get(); }
	public int getisbn() {return product_isbn; }
	public String getAuthor() { return product_author; }
	public String getPublisher() { return product_publisher; }
	public int getvolume() { return product_volume; }
	public String getArtist() { return product_artist; }
	public String getCategory() { return category.get(); }
	public String getDescription() { return description.get(); }
	public int getPrice() { return price.get(); }
	public int getRating() { return rating; }
	public ImageView getImage() { return image; }
	public int getNumber() { return product_quantity; }
	public int getVendor_Id() { return vendor_id; }

	// setter
	public void setid(int id) {this.product_id = id;}
	public void setName(String name) { this.name.set(name) ;}
	public void setIsbn(int isbn) {this.product_isbn = isbn;}
	public void setPublisher(String publisher) {this.product_publisher = publisher;	}
	public void setVolume(int volume) {	this.product_volume = volume;}
	public void setArtist(String artist) {	this.product_artist = artist;}
	public void setVendor_id(int vendor_id) {this.vendor_id = vendor_id;}
	public void setPrice(int price) { this.price.set(price); }
	public void setCategory(String category) { this.category.set(category);}
	public void setDescription(String description) { this.description.set(description);}
	public void setRating(int rating) { this.rating = rating; }
	public void setImage(ImageView image) { this.image = image; }
	public void setNumber(int number) { this.product_quantity = number; }
	public void setVendor_Id(int vendor_id) { this.vendor_id = vendor_id; }
	public void setAuthor(String author) {this.product_author = author;	}
 }
