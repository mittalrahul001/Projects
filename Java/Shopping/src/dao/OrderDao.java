package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Order;

public class OrderDao {

	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	public Order create(Order order) {

		// Get a connection
		try {

			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query to insert a record to the bank table
		String query = "INSERT INTO rmittal6_order  (customer_email,order_price,order_date) VALUES (?, ?,?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters to the query
			statement.setString(1, order.getCustomer_email());
			statement.setInt(2, order.getOrder_price());
			statement.setTimestamp(3, new Timestamp(order.getDate().getTime()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model

				order.setOrder_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			order = null;
			System.out.println("Error Creating Order: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the bank object that was inserted with the id field set.
		return order;
	}



public ObservableList<Order> Orders(Customer customer) throws SQLException{

	ObservableList<Order> list1 =FXCollections.observableArrayList();
	//Get a connection
		try {
          connection = DriverManager.getConnection(url, username, password);
      } catch(SQLException e) {
          System.out.println("Error creating connection to database: " + e);
          System.exit(-1);
      }
		//String email = customer.getEmailAddress();
		//Query to insert a record to the bank table
		String query = "Select * from rmittal6_order where customer_email = ?";
			//Use prepared statements to avoid SQL injection attacks
		try(
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
         statement.setString(1, customer.getEmailAddress());
          //Execute the insert

			ResultSet resultSet = statement.executeQuery();
			Order order = new Order();

        while( resultSet.next()){
        	//Set the id field of the database to the model
        	order.setOrder_id(resultSet.getInt(1));
        	order.setCustomer_email(resultSet.getString(2));
        	order.setOrder_price(resultSet.getInt(3));
        	order.setDate(resultSet.getDate(4));

        	//OrderDao orderdao = new OrderDao();
        	list1.add(order);
//        	 Product.setBook_id(resultSet.getInt(1));
          }
      } catch(SQLException e){
    	  customer = null;
          System.out.println("Error while reading customer " + e);
      }
		//Close the connection to the database - Very important!!!
		try {
          connection.close();
          connection = null;
      } catch(SQLException e) {
          System.out.println("Error closing connection: " + e);
      }
		//Return the bank object that was inserted with the id field set.

		return list1;
	}
}