package dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Product;

public class ProductDao {

	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";


	public Product create(Product product) throws IOException {
  		//Get a connection
  		try {
              connection = DriverManager.getConnection(url, username, password);
          } catch(SQLException e) {
              System.out.println("Error creating connection to database: " + e);
              System.exit(-1);
          }
  		//Query to insert a record to the bank table
  		String query = "INSERT INTO rmittal6_product "
  		+ "(product_name,product_price,product_category,product_isbn,product_author,product_publisher,"
  		+ "product_quantity,product_volume,product_artist,product_desc,vendor_id,product_rating,product_img)"
  		+ " VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?) ;";
  		/*String query1 = "insert into TBLStudentView (book_name,ISSUE_DATE,due_date,Student_id,book_id) values (null,null,null,null,?);";*/

  		//Use prepared statements to avoid SQL injection attacks
  		try(
  			 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
  			BufferedImage bImage = SwingFXUtils.fromFXImage(product.getImage().getImage(), null);
	         ByteArrayOutputStream s = new ByteArrayOutputStream();
	         ImageIO.write(bImage, "png", s);
	         byte[] res  = s.toByteArray();
  			//Set the parameters to the query
              statement.setString(1, product.getName());
              statement.setInt(2, product.getPrice());
              statement.setString(3, product.getCategory());
              statement.setInt(4, product.getisbn());
              statement.setString(5, product.getAuthor());
              statement.setString(6, product.getPublisher());
              statement.setInt(7, product.getNumber());
              statement.setInt(8, product.getvolume());
              statement.setString(9, product.getArtist());
              statement.setString(10, product.getDescription());
              statement.setInt(11, product.getVendor_Id());
              statement.setInt(12, product.getRating());
              statement.setBytes(13, res);

              //Execute the insert
              statement.executeUpdate();
              //To get the primary key (id) of the newly inserted record
              ResultSet resultSet = statement.getGeneratedKeys();
              if(resultSet.next()) {
              	//Set the id field of the database to the model
              	product.setid(resultSet.getInt(1));
            	//  book.setBook_id(resultSet.getInt(1));
              }
          } catch(SQLException e){
        	  product = null;
              System.out.println("Error Creating Product: " + e);
          }

  		//Close the connection to the database - Very important!!!
  		try {
              connection.close();
              connection = null;
          } catch(SQLException e) {
              System.out.println("Error closing connection: " + e);
          }
  		//Return the bank object that was inserted with the id field set.
  		return product;
  	}

	public ObservableList<Product> Products() throws SQLException{
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }

	    ObservableList<Product> list1 =FXCollections.observableArrayList();
	    Statement statement = connection.createStatement();
	    String query = "Select * from rmittal6_product";
	    try{
			//Set the parameters to the query
	        //To get the primary key (id) of the newly inserted record

	        ResultSet resultSet = statement.executeQuery(query);
	        while( resultSet.next()){
	        	Product product = new Product();
	        	product.setid(resultSet.getInt("product_id"));
	        	product.setName(resultSet.getString("product_name"));
	        	product.setPrice(resultSet.getInt("product_price"));
	        	product.setCategory(resultSet.getString("product_category"));
	        	product.setIsbn(resultSet.getInt("product_isbn"));
	        	product.setAuthor(resultSet.getString("product_author"));
	        	product.setPublisher(resultSet.getString("product_publisher"));
	        	product.setNumber(resultSet.getInt("product_quantity"));
	        	product.setVolume(resultSet.getInt("product_volume"));
	        	product.setArtist(resultSet.getString("product_artist"));
	        	product.setDescription(resultSet.getString("product_desc"));
	        	product.setid(resultSet.getInt("vendor_id"));
	        	product.setRating(resultSet.getInt("product_rating"));

	        	 //box1.getItems().add(resultSet.getBytes("product_img"));
	               InputStream input = new ByteArrayInputStream(resultSet.getBytes("product_img"));
	               Image image = new Image(input);
	               ImageView img_view = new ImageView();
	               img_view.setImage(image);
	        	   product.setImage(img_view);

	            System.out.println(resultSet.getInt(1));
	            list1.add(product);
	        }
	      }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");
	    }
		return list1;
	}
}


