package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.OrderDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Customer;
import model.Order;
import model.Product;

public class MainApp extends Application {

	private Stage primaryStage, mainStage;
	private BorderPane rootLayout;
	private AnchorPane mainView, viewBasket, customerPane, addCustomer, loginView, passwordPane, orderPane;

	private LoginController loginViewController;
	private MainViewController mainViewController;
	private BasketController cartController;
	private CustomerController customerController;
	private AddCustomerController1 addCustomerController1;
	private RootLayoutController rootLayoutController;
	private OrderController orderController;

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));

		// display the login window
		showLoginView();
	}

	public Stage getPrimaryStage() { return primaryStage; }

	public MainApp() {}

	// loads stage and scene for the customer login
	public void showLoginView() {
        try {
        	// Load LoginView.fxml from 'view' package
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/LoginView.fxml"));
            loginView = (AnchorPane) loader.load();

            // Give the controller access to MainApp and primaryStage
            loginViewController = loader.getController();
            loginViewController.setApp(this);
            loginViewController.setPrimaryStage(primaryStage);

            // Set the scene
            Scene loginScene = new Scene(loginView);
            primaryStage.setTitle("Login");
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	// handles change from loginView to mainView
    public void callMainView(Customer customer) throws Exception{
        this.showRootLayout(customer);
        this.primaryStage.close();
    }

    // adds a menubar to the main view
	public void showRootLayout(Customer customer) throws Exception {
		try {
			// Load RootLayout.fxml from 'view' package
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/RootLayout.fxml"));
			rootLayout = loader.load();
			mainStage = new Stage();
			mainStage.setTitle("Shopping Client");
			mainStage.getIcons().add(new Image("file:resources/images/icon.png"));
			mainStage.setMinWidth(1000.00);
			mainStage.setMinHeight(600.00);

			// Set the scene
			Scene rootScene = new Scene(rootLayout);
			mainStage.setScene(rootScene);
			mainStage.show();

            // Give the controller access to MainApp and mainStage
            rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);
            rootLayoutController.setMainStage(mainStage);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// calls the MainView to be loaded in the mainStage
		showMainView(customer);
	}

	// shows the main window with product catalogue
	public void showMainView(Customer customer) throws Exception {
		try {
			// Load MainView.fxml from 'view' package
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/MainView.fxml"));
			mainView = (AnchorPane) loader.load();
			rootLayout.setCenter(mainView);

			// Give the controller access to the main app and
	        // sends information about the logged-in customer
	        mainViewController = loader.getController();
	        mainViewController.setMainApp(this, customer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void callAdminView() throws Exception
	{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();
            this.primaryStage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void callAddProductView() throws Exception
	{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();
        } catch(Exception e) {
			e.printStackTrace();
		}
	}

// BASKET HANDLING

	// empty basket list
	private ObservableList<Product> basketList = FXCollections.observableArrayList();

	// adds a product to the basket list, including it's selected quantity
	public void addBasket(Product product, int quantity) {
		basketList.add(new Product(0,product.getName(),product.getPrice()*quantity,product.getCategory(),0,null,null,quantity,0,null,null,0,0,product.getImage()));
	}

	// gives access to the basketList
	public ObservableList<Product> getBasketList(){ return basketList; }

	// shows the actual basket view
	public void showViewBasket(Customer customer) {
		try{
	        // Load CartView.fxml from 'view' package
	        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/BasketView.fxml"));
	        viewBasket = (AnchorPane) loader.load();

	        // Set the scene
	        Stage basketStage = new Stage();
	        basketStage.setTitle("Your Basket");
	        basketStage.initOwner(mainStage);
	        Scene orderScene = new Scene(viewBasket);
	        basketStage.setScene(orderScene);

	        // Give the controller access to the MainApp and basketStage and
	        // sends information about the logged-in customer
	        cartController = loader.getController();
	        cartController.setEditStage(basketStage);
	        cartController.setMainApp(this, customer);
	        cartController.getPrice();

	        // Display the CartView view and wait for user to close it
	        basketStage.showAndWait();
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}

	// calculates the total price of the order and rounds the double to 2 decimals
    public int getTotalPrice() {
    	int price = 0;
    	for (int i=0; i<getBasketList().size();i++) {
    		price = price + getBasketList().get(i).getPrice();
    	}
    	price = Math.round(price * 100);
    	price = price / 100;
    	return price;
    }

    // updates the totalPrice label in Mainview / BasketView
	public void updateUIMainView() {
		mainViewController.setCustomerLabel();
		mainViewController.setLabelText();
	}

// ORDER HANDLING

	// empty basket list
	private ObservableList<Order> orderList = FXCollections.observableArrayList();

	// creates a order for the customer and saves it to the orderList
	public void saveOrder(Customer customer, int totalSum) {
		Order order = new Order();
		order.setCustomer_email(customer.getEmailAddress());
		order.setOrder_price(totalSum);
		Date date = new Date();
		order.setDate(date);

		OrderDao orderdao = new OrderDao();
		orderdao.create(order);

	}

	public ObservableList<Order> getOrderList(){ return orderList; }

	public void showOrder(Customer customer) throws Exception {
		try{
	        // Load CustomerView.fxml from 'view' package
	        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/OrderView.fxml"));
	        orderPane = (AnchorPane) loader.load();

	        // create and new scene and set it to the primaryStage
	        Scene orderScene = new Scene(orderPane);
	        primaryStage.setTitle("My Orders");
	        primaryStage.setScene(orderScene);

	        // Give the input Person to the controller
	        orderController = loader.getController();
	        orderController.setMainApp(this, customer);

	        // Display the CustomerProfile view and wait for user to close it
	        primaryStage.show();
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}

	// shows the password edit window
	public void showPassword(Customer customer) {
		try{
	        // Load Password.fxml from 'view' package
	        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/PasswordView.fxml"));
	        passwordPane = (AnchorPane) loader.load();

	        // create and new scene and set it to the primaryStage
	        Scene passwordScene = new Scene(passwordPane);
	        primaryStage.setTitle("Change Password");
	        primaryStage.setScene(passwordScene);

	        // Display the Password view and wait for user to close it
	        primaryStage.show();
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}

	public void updateLoginUI() {
		this.loginViewController.setApp(this);
	}

	// shows the customer profile
	public void showCustomer(Customer customer) {
		try{
	        // Load CustomerView.fxml from 'view' package
	        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/CustomerView.fxml"));
	        customerPane = (AnchorPane) loader.load();

	        Scene customerScene = new Scene(customerPane);
	        //primaryStage.initOwner(mainStage);
	        //primaryStage.initModality(Modality.WINDOW_MODAL);
	        primaryStage.setTitle("My Profile");
	        primaryStage.setScene(customerScene);

	        // Give the input Person to the controller
	        customerController = loader.getController();
	        customerController.setPrimaryStage(primaryStage);
	        customerController.showCustomerDetails(customer);
	        customerController.setMainApp(this, customer);

	        // Display the CustomerProfile view and wait for user to close it
	        primaryStage.show();
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}

	public void addCustomer(Customer customer) {
		try{
	        // Load AddCustomer.fxml from 'view' package
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/AddCustomerView.fxml"));
	        addCustomer = (AnchorPane) loader.load();

	        // Create new scene and set to primaryStage
	        Scene addCustomerScene = new Scene(addCustomer);
	        primaryStage.setTitle("New Customer");
	        primaryStage.setScene(addCustomerScene);

	        // Give the input Person to the controller
	        addCustomerController1 = loader.getController();
	        addCustomerController1.setMainApp(this, customer);

	        // Display the CustomerProfile view and wait for user to close it
	        primaryStage.show();
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}
}