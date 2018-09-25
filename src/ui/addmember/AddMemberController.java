package ui.addmember;

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


public class AddMemberController implements Initializable {

	@FXML
    private AnchorPane rootPane;
	
	@FXML
    private JFXTextField nameTxt;
    @FXML
    private JFXTextField idTxt;
    @FXML
    private JFXTextField phoneTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    
    DatabaseHandler databaseHandler;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		databaseHandler = DatabaseHandler.getInstance();
		checkData();
	}
	
	@FXML
    void addMember(ActionEvent event) {
		
		String memberName = nameTxt.getText(); 
		String memberID = idTxt.getText(); 
		String memberPhone = phoneTxt.getText();
		String memberEmail = emailTxt.getText();
		
		boolean flag = memberName.isEmpty() || memberID.isEmpty() || memberPhone.isEmpty() || memberEmail.isEmpty();
		
		if (flag) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Every field must be filled with data!");
			alert.showAndWait();
			return;
		}
		
		String action = "INSERT INTO MEMBER VALUES (" 
						+ "'" + memberID + "', "
						+ "'" + memberName + "', "
						+ "'" + memberPhone + "', "
						+ "'" + memberEmail + "'"
						+ ");";
		
		System.out.println(action);
		if (databaseHandler.executeAction(action)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("New Member SUCCESSFULLY added!");
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
    	
    	String query = "SELECT name FROM MEMBER";   
    	ResultSet rs = databaseHandler.executeQuery(query);
    	
    	try {
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
