package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Vendor;

public class VendorDao {
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";


  //Method to save a bank model to database
  	public Vendor create(Vendor vendor) {
  		//Get a connection
  		try {
              connection = DriverManager.getConnection(url, username, password);
          } catch(SQLException e) {
              System.out.println("Error creating connection to database: " + e);
              System.exit(-1);
          }
  		//Query to insert a record to the bank table
  		String query = "INSERT INTO rmittal6_vendor (vendor_name,vendor_address,vendor_phone,vendor_email) VALUES (?, ?, ?,?) ;";

  		//Use prepared statements to avoid SQL injection attacks
  		try(
  				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
  			//Set the parameters to the query
              statement.setString(1, vendor.getVendor_name());
              statement.setString(2, vendor.getVendor_address());
              statement.setLong(3, vendor.getVendor_Phone());
              statement.setString(4, vendor.getVendor_email());
              //Execute the insert
              statement.executeUpdate();
              //To get the primary key (id) of the newly inserted record
              ResultSet resultSet = statement.getGeneratedKeys();
              if(resultSet.next()) {
              	//Set the id field of the database to the model
                	vendor.setVendor_id(resultSet.getInt(1));
            	  //book.setBook_id(resultSet.getInt(1));
              }
          } catch(SQLException e){
          	vendor = null;
              System.out.println("Error Creating Bank: " + e);
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
  		return vendor;
  	}

  	public Vendor read(int id)
  	{
  	//Get a connection
  		try {
              connection = DriverManager.getConnection(url, username, password);
          } catch(SQLException e) {
              System.out.println("Error creating connection to database: " + e);
              System.exit(-1);
          }
  		//Query to insert a record to the bank table
  		String query = "Select * from rmittal6_vendor where vendor_id = ? ;";
  		Vendor vendor = new Vendor();
  		//Use prepared statements to avoid SQL injection attacks
  		try(
  			 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
  			statement.setInt(1, id);
            //Execute the insert
            statement.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
            	//Set the id field of the database to the model
              	vendor.setVendor_id(resultSet.getInt(1));
              	vendor.setVendor_name(resultSet.getString(2));
              	vendor.setVendor_address(resultSet.getString(3));
              	vendor.setVendor_phone(resultSet.getInt(4));
              	vendor.setVendor_email(resultSet.getString(5));
          	  //book.setBook_id(resultSet.getInt(1));
            }

  		}
  		catch(SQLException e){
          	vendor = null;
              System.out.println("Error Reading Vendor: " + e);
          }
  		//Return the bank object that was inserted with the id field set.
  		return vendor;
  	}

//  //Method to save a bank model to database
//  	public Book delete(Book book1) {
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
//              statement.setInt(1, book1.getBook_id1());
//              //Execute the insert
//               statement.executeUpdate();
//
//              //To get the primary key (id) of the newly inserted record
//              ResultSet resultSet = statement.getGeneratedKeys();
//
//             if(resultSet.next()) {
//              	//Set the id field of the database to the model
//
//            	  book1.setBook_id1(resultSet.getInt(1));
//              }
//          } catch(SQLException e){
//          	book1 = null;
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
//  		return book1;
//  	}
//
//
//  	public Book update(Book book2) {
//  		//Get a connection
//  		try {
//              connection = DriverManager.getConnection(url, username, password);
//          } catch(SQLException e) {
//              System.out.println("Error creating connection to database: " + e);
//              System.exit(-1);
//          }
//  		//Query to insert a record to the bank table
//  		String query = "Update TBLBooks set book_name=?,author=? where book_id=?";
//
//  		//Use prepared statements to avoid SQL injection attacks
//  		try(
//  			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
//  			//Set the parameters to the query
//             // statement.getInteger(1, book.setBook_id(Integer.parseInt(Id)));
//
//           statement.setString(1, book2.getBook_name());
//           statement.setString(2, book2.getAuthor());
//           statement.setInt(3, book2.getBook_id());
//              //Execute the insert
//               statement.executeUpdate();
//
//              //To get the primary key (id) of the newly inserted record
//              ResultSet resultSet = statement.getGeneratedKeys();
//
//             if(resultSet.next()) {
//              	//Set the id field of the database to the model
//
//            	  book2.setBook_id(resultSet.getInt(1));
//              }
//          } catch(SQLException e){
//          	book2 = null;
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
//  		return book2;
//  	}
//
//




//  	public ObservableList<Book> Books() throws SQLException{
//
//
//  	    try{
//
//  	    	connection = DriverManager.getConnection(url, username, password);
//  	    }
//  	    catch(SQLException e) {
//  	        System.out.println("Error creating connection to database: " + e);
//  	        System.exit(-1);
//  	    }
//  	    ObservableList<Book> list1 =FXCollections.observableArrayList();
//  	    Statement statement = connection.createStatement();
//  	    String query = "Select * from tblbooks";
//  	    try{
//  			//Set the parameters to the query
//
//
//  	        //To get the primary key (id) of the newly inserted record
//
//  	        ResultSet resultSet = statement.executeQuery(query);
//  	        while( resultSet.next()){
//  	        	Book book = new Book();
//  	            book.setBook_id(resultSet.getInt("book_id"));
//
//  	            book.setBook_name(resultSet.getString("book_name"));
//  	           book.setAuthor(resultSet.getString("author"));
//
//
//  	            System.out.println(resultSet.getInt(1));
//  	            list1.add(book);
//  	        }
//  	      }
//  	    catch(Exception e){
//  	          e.printStackTrace();
//  	          System.out.println("Error on Building Data");
//  	    }
//  		return list1;
//  	}


}