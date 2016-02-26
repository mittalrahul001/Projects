package controller;

import model.Customer;
import model.Order;

import java.sql.SQLException;

import dao.OrderDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class OrderController {

	@FXML private ListView<String> orderlistView;
	@FXML private ObservableList<String> p = FXCollections.observableArrayList();

	private Stage primaryStage;
	private MainApp mainApp;
	private Customer loggedInCustomer;

	// Reference to the mainApp
	public void setMainApp(MainApp mainApp, Customer customer) throws Exception {
		this.mainApp = mainApp;
		this.loggedInCustomer = customer;
		this.viewOrders();

	}
	// display the orders in the listView
	private void viewOrders() throws Exception {

		OrderDao orderDao = new OrderDao();
		ObservableList<Order> orderlist =FXCollections.observableArrayList();
		orderlist = orderDao.Orders(loggedInCustomer);
		// checks if any order in the observableList
		 if (orderlist.size() > 0) {
			 // finds orders that match to the logged in customer and display them
			 for ( int i=0;i<orderlist.size();i++) {
				// if (mainApp.getOrderList().get(i).getCustomer_email().equals(loggedInCustomer)) {
					 System.out.println("customers match");
					 Order order = orderlist.get(i);
					 p.add(order.getDate()+"  -----  Total Price: $"+order.getOrder_price());
				 //}
		 }
			 orderlistView.setItems(p);
		 }
	 }

	// returns to customerView when ok is clicked
	@FXML public void handleOk() {
		mainApp.showCustomer(loggedInCustomer);
	}

	// returns to customerView when cancel is clicked
	@FXML public void handleCancel() {
		mainApp.showCustomer(loggedInCustomer);
	}

}
