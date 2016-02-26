package controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Customer;
import model.Product;
import model.ProductDescription;

public class MainViewController {
	@FXML private TableView<Product> tableView;
	@FXML private TableView<Product> shoppingCart;
	@FXML private TableColumn<Product, String> nameColumn;
	@FXML private TableColumn<Product, String> priceColumn;
	@FXML private TableColumn<Product, String> categoryColumn;
	@FXML private TableColumn<Product, String> cartProductColumn;
	@FXML private TableColumn<Product, String> cartPriceColumn;
	@FXML private Label customerName, totalSum, items, name, price, category, ratingLabel, description;
	@FXML private TextField search;
	@FXML private ImageView imageView;
	@FXML private ImageView ratingOne, ratingTwo, ratingThree, ratingFour, ratingFive;
    @FXML private ComboBox<Integer> comboQuantity;
    @FXML private ComboBox<Integer> comboRating;

	private MainApp mainApp;
	private int quant, rating;
	private List<Integer> ratingArray;
	private Customer loggedInCustomer;

    @FXML
    private void initialize() {
    // Initialize the person table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));

        tableView.getSelectionModel().selectedItemProperty().addListener(
        		new ChangeListener<Product>() {
                    public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                    	showProductDetails(newValue);
                    	System.out.println(newValue.getName());
                    }
                });
    }

    // Reference to mainApp == receives the logged in customer from the loginView
    public void setMainApp(MainApp mainApp, Customer customer) throws Exception {
        this.mainApp = mainApp;
        this.addToList();
        loggedInCustomer = customer;
        // displays the customer name top left
        setCustomerLabel();
        this.populateCombo();
        // sets the default product quantity to 1
        tableView.setItems(productData);
    }

	public MainViewController() { }

	// populates the product list
	public void addToList() throws SQLException {

		ProductDao productdao = new ProductDao();
		productData = productdao.Products();
			}

	// manages the product details on the right in the main View
    private void showProductDetails(Product Product){
        if (Product != null){
            // Fill labels with details from selected Product
            name.setText(Product.getName());
            price.setText(String.valueOf(Product.getPrice()));

            category.setText(Product.getCategory());
           // Image productImage = new Image(Product.getImage());
            imageView.setImage(Product.getImage().getImage());
            description.setText(Product.getDescription());
            //calculateRating();

        } else{
        	System.out.println("Blank");
            // Product is null so set labels to be blank
            name.setText("");
            price.setText("no price");
            category.setText("");
        }
    }

    // receives the rating input
    @FXML public void handleRating() {
    	Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
    	int newRating = comboRating.getSelectionModel().getSelectedItem();
    	//ratingArray = selectedProduct.getRating();
        if (ratingArray == null) {
        	ratingArray = new ArrayList<Integer>();
        }
    	ratingArray.add(newRating);
    	//selectedProduct.setRating(ratingArray);
    	calculateRating();
    }

    // calculates average rating and manages visibility of the stars
    public void calculateRating() {
    	Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
    	//ratingArray = selectedProduct.getRating();
        if (ratingArray == null) {
        	rating = 1;
        } else {
        	int sum = 0;
        	for (int i : ratingArray) {
        		sum += i;
        	}
        	rating = sum / ratingArray.size();
        }
        switch (rating) {
        	case 5 :
        		ratingOne.setVisible(true);
        		ratingTwo.setVisible(true);
        		ratingThree.setVisible(true);
        		ratingFour.setVisible(true);
        		ratingFive.setVisible(true);
        		break;
        	case 4 :
        		ratingOne.setVisible(true);
        		ratingTwo.setVisible(true);
        		ratingThree.setVisible(true);
        		ratingFour.setVisible(true);
        		ratingFive.setVisible(false);
        		break;
        	case 3 :
        		ratingOne.setVisible(true);
        		ratingTwo.setVisible(true);
        		ratingThree.setVisible(true);
        		ratingFour.setVisible(false);
        		ratingFive.setVisible(false);
        		break;
        	case 2 :
        		ratingOne.setVisible(true);
        		ratingTwo.setVisible(true);
        		ratingThree.setVisible(false);
        		ratingFour.setVisible(false);
        		ratingFive.setVisible(false);
        		break;
        	case 1 :
        		ratingOne.setVisible(true);
        		ratingTwo.setVisible(false);
        		ratingThree.setVisible(false);
        		ratingFour.setVisible(false);
        		ratingFive.setVisible(false);
        		break;
        }
    }

    // updates the total price and item number in the labels top right
    @FXML public void setLabelText() {
    	items.setText(Integer.toString(mainApp.getBasketList().size()));
    	if (mainApp.getBasketList().size() != 0) {
    		totalSum.setText("$"+Double.toString(mainApp.getTotalPrice()));
    	} else {
    		totalSum.setText("$0.00");
    	}
    }

    // opens the basket window
    @FXML private void handleViewOrder() { mainApp.showViewBasket(loggedInCustomer); }

    // opens the customerView
    @FXML private void handleViewCustomer() { mainApp.showCustomer(loggedInCustomer); }


    // adds items to the shopping cart
	@FXML private void handleAddToCart() {
		Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			// checks selected quantity
			quant = (int) comboQuantity.getSelectionModel().getSelectedItem();
			mainApp.addBasket(selectedProduct, quant);
			// update the total price label
			setLabelText();
			// reset the combobox selection
			comboQuantity.getSelectionModel().select(0);
		} else {
			System.out.println("No item selected!");
		}
	 }


	private void populateCombo() {
		// populate comboBox for number of products and set the default to '1'
		comboQuantity.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		comboQuantity.getSelectionModel().select(0);
		// populate comboBox for rating, sets default to null
		comboRating.getItems().addAll(1,2,3,4,5);
		comboRating.getSelectionModel().select(null);
	}

	@FXML public void setCustomerLabel() {
		customerName.setText(loggedInCustomer.getFirstName()+" "+loggedInCustomer.getLastName());
	}

	// contains the products displayed in mainView
	private ObservableList<Product> productData = FXCollections.observableArrayList();

	public ObservableList<Product> getProductData() { return productData; }
}