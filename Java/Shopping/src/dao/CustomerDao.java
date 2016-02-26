package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Product;

public class CustomerDao {

	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";


	public Customer create(Customer customer) {
  		//Get a connection
  		try {
              connection = DriverManager.getConnection(url, username, password);
          } catch(SQLException e) {
              System.out.println("Error creating connection to database: " + e);
              System.exit(-1);
          }
  		//Query to insert a record to the bank table
  		String query = "INSERT INTO rmittal6_customer (customer_email,first_name,last_name,cust_password,street,city) VALUES (?, ?, ?,?,?,?) ;";
  		/*String query1 = "insert into TBLStudentView (book_name,ISSUE_DATE,due_date,Student_id,book_id) values (null,null,null,null,?);";*/

  		//Use prepared statements to avoid SQL injection attacks
  		try(
  				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
  			//Set the parameters to the query
               statement.setString(1, customer.getEmailAddress());
              statement.setString(2, customer.getFirstName());
              statement.setString(3, customer.getLastName());
              statement.setString(4, customer.getPassword());
              statement.setString(5, customer.getStreet());
              statement.setString(6, customer.getCity());
              //Execute the insert
              statement.executeUpdate();
              //To get the primary key (id) of the newly inserted record
              ResultSet resultSet = statement.getGeneratedKeys();
              if(resultSet.next()) {
              	//Set the id field of the database to the model
              /*	bank.setId(resultSet.getInt(1));*/
            	//  book.setBook_id(resultSet.getInt(1));
              }
          } catch(SQLException e){
        	  customer = null;
              System.out.println("Error Creating Product: " + e);
          }

  		/*try(
  				PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
  			//Set the parameters to the query
              statement1.setInt(1, book.getBook_id());

              //Execute the insert
              statement1.executeUpdate();
              //To get the primary key (id) of the newly inserted record
              ResultSet resultSet1 = statement1.getGeneratedKeys();
              if(resultSet1.next()) {
              	//Set the id field of the database to the model
              	bank.setId(resultSet.getInt(1));
            	  book.setBook_id(resultSet1.getInt(1));
              }
          } catch(SQLException e){
          	book = null;
              System.out.println("Error Creating Bank: " + e);
          }*/
  		//Close the connection to the database - Very important!!!
  		try {
              connection.close();
              connection = null;
          } catch(SQLException e) {
              System.out.println("Error closing connection: " + e);
          }
  		//Return the bank object that was inserted with the id field set.
  		return customer;
  	}

//
//  //Method to save a bank model to database
//  	public Customer delete(Customer customer) {
//  		//Get a connection
//  		try {
//              connection = DriverManager.getConnection(url, username, password);
//          } catch(SQLException e) {
//              System.out.println("Error creating connection to database: " + e);
//              System.exit(-1);
//          }
//  		//Query to insert a record to the bank table
//  		String query = "DELETE FROM TBLBooks WHERE book_id=?";
//  		//Use prepared statements to avoid SQL injection attacks
//  		try(
//  			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
//  			//Set the parameters to the query
//             // statement.getInteger(1, book.setBook_id(Integer.parseInt(Id)));
//             // statement.setInt(1, Product.getBook_id1());
//              //Execute the insert
//               statement.executeUpdate();
//
//              //To get the primary key (id) of the newly inserted record
//              ResultSet resultSet = statement.getGeneratedKeys();
//
//             if(resultSet.next()) {
//              	//Set the id field of the database to the model
//
//            //	 Product.setBook_id1(resultSet.getInt(1));
//              }
//          } catch(SQLException e){
//          	Product = null;
//              System.out.println("Error Creating Bank: " + e);
//          }
//  		//Close the connection to the database - Very important!!!
//  		try {
//              connection.close();
//              connection = null;
//          } catch(SQLException e) {
//              System.out.println("Error closing connection: " + e);
//          }
//  		//Return the bank object that was inserted with the id field set.
//  		return Product;
//  	}

	public Customer read(String email)
	{
		//Get a connection
  		try {
              connection = DriverManager.getConnection(url, username, password);
          } catch(SQLException e) {
              System.out.println("Error creating connection to database: " + e);
              System.exit(-1);
          }
  		//String email = customer.getEmailAddress();
  		//Query to insert a record to the bank table
  		String query = "Select * from rmittal6_customer where customer_email = ?";
  		Customer customer = new Customer();
  		String resultset_email = null,resultset_first=null;
  		//Use prepared statements to avoid SQL injection attacks
  		try(
  			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
  			//Set the parameters to the query
             statement.setString(1, email);
              //Execute the insert

  			ResultSet resultSet = statement.executeQuery();
  			if(resultSet != null)
  			{
  				System.out.println("Resultset is not empty");
  			}
	        while( resultSet.next()){
	        	resultset_email = resultSet.getString(1);
	        	resultset_first = resultSet.getString(2);
              	//Set the id field of the database to the model
            	 customer.setEmailAddress(resultSet.getString(1));
            	 customer.setFirstName(resultSet.getString(2));
            	 customer.setLastName(resultSet.getString(3));
            	 customer.setPassword(resultSet.getString(4));
            	 customer.setStreet(resultSet.getString(5));
            	 customer.setCity(resultSet.getString(6));

//            	 Product.setBook_id(resultSet.getInt(1));
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
  	 String email_id = customer.getEmailAddress();
   	 String cust_name = customer.getFirstName();
   	 System.out.println("Resultset email:" + resultset_email);
   	 System.out.println("Resultset first" + resultset_first);
   	 System.out.println(email_id);
   	 System.out.println(cust_name);
  		return customer;
  	}

}
