package ui.addbook;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddBookController implements Initializable {

	@FXML
    private AnchorPane rootPane;
	
    @FXML
    private JFXTextField titleTxt;
    @FXML
    private JFXTextField idTxt;
    @FXML
    private JFXTextField authorTxt;
    @FXML
    private JFXTextField publisherTxt;

    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    
    DatabaseHandler databaseHandler;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		databaseHandler = new DatabaseHandler();
		checkData();
	}
	
	@FXML
    void addBook(ActionEvent event) {
		
		String bookTitle = titleTxt.getText();
		String bookID = idTxt.getText();
		String bookAuthor = authorTxt.getText();
		String bookPublisher = publisherTxt.getText();
		
		if (bookTitle.isEmpty() || bookID.isEmpty() || bookAuthor.isEmpty() || bookPublisher.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Every field must be filled with data!");
			alert.showAndWait();
			return;
		}
		
		String action = "INSERT INTO BOOK VALUES (" 
						+ "'" + bookID + "', "
						+ "'" + bookTitle + "', "
						+ "'" + bookAuthor + "', "
						+ "'" + bookPublisher + "', "
						+ true
						+ ");";
		
		System.out.println(action);
		if (databaseHandler.executeAction(action)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("New Book SUCCESSFULLY added!");
			alert.showAndWait();
		} else {  //ERROR
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Failed!");
			alert.showAndWait();
		}
    }

    @FXML
    void cancel(ActionEvent event) {
    	
    	Stage stage = (Stage) rootPane.getScene().getWindow();
    	stage.close();
    }
    
    void checkData() {
    	
    	String query = "SELECT title FROM BOOK";   
    	ResultSet rs = databaseHandler.executeQuery(query);
    	
    	try {
			while (rs.next()) {
				String title = rs.getString("title");
				System.out.println(title);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
