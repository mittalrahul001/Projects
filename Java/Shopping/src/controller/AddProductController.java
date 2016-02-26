package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import dao.ProductDao;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Product;

public class AddProductController {

	@FXML private TextField prod_name,prod_desc,prod_quant,prod_author,prod_category,prod_rating;
	@FXML private TextField prod_isbn,prod_publisher,prod_artist,prod_volume,vendor_id;
	@FXML private TextField prod_price;
	@FXML private Button submit,prod_image;
	@FXML private ImageView browseimage;


	@FXML
    public void initialize() {}

	@FXML private void browseimageclick(ActionEvent event)  throws Exception
	{
		 FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            browseimage.setImage(image);

        } catch (Exception ex) {
        	ex.printStackTrace();
            }
	}

    @FXML public void submitOnClick(ActionEvent event) throws Exception {
    	System.out.println("Button Pressed");
    	Product product = new Product();

    	product.setName(prod_name.getText());
    	product.setPrice(Integer.parseInt(prod_price.getText()));
    	product.setCategory(prod_category.getText());
    	product.setIsbn(Integer.parseInt(prod_isbn.getText()));
    	product.setAuthor(prod_author.getText());
    	product.setPublisher(prod_publisher.getText());
    	product.setVendor_id(Integer.parseInt(vendor_id.getText()));
    	product.setNumber(Integer.parseInt(prod_quant.getText()));
    	product.setVolume(Integer.parseInt(prod_volume.getText()));
    	product.setArtist(prod_artist.getText());
    	product.setDescription(prod_desc.getText());
    	product.setRating(Integer.parseInt(prod_rating.getText()));
    	product.setImage(browseimage);
        ProductDao productdao = new ProductDao();
        productdao.create(product);
        System.out.println("Dao created");
    }

}
