package ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
	@FXML
    private JFXButton addMemberBtn;
    @FXML
    private JFXButton addBookBtn;
    @FXML
    private JFXButton viewMembersBtn;
    @FXML
    private JFXButton viewBooksBtn;
    @FXML
    private JFXButton settingsBtn;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	private void loadWindow(String location, String title) {
    	
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(location));
			
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    @FXML
    private void loadAddBook(ActionEvent event) {
    	loadWindow("/ui/addbook/FXMLAddBook.fxml", "Add New Book");
    }

    @FXML
    private void loadAddMember(ActionEvent event) {
    	loadWindow("/ui/addmember/FXML_AddMember.fxml", "Add New Member");
    }

    @FXML
    private void loadSettings(ActionEvent event) {
    	
    }

    @FXML
    private void loadViewBooks(ActionEvent event) {
    	loadWindow("/ui/booklist/FXML_BookList.fxml", "View Books Registered");
    }

    @FXML
    private void loadViewMembers(ActionEvent event) {
    	loadWindow("/ui/memberlist/FXML_MemberList.fxml", "View Members Registered");
    }
    
}