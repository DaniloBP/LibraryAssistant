package ui.main;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
    
    @FXML
    private JFXTextField bookIdTxt;
    @FXML
    private JFXTextField memberIdTxt;
    
    @FXML
    private Text bookNameLabel;
    @FXML
    private Text bookAuthorLabel;
    @FXML
    private Text bookStatusLabel;
   
    @FXML
    private HBox bookInfo;
    @FXML
    private HBox memberInfo;
    
    DatabaseHandler databaseHandler;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXDepthManager.setDepth(bookInfo, 1);
		JFXDepthManager.setDepth(memberInfo, 1);
		
		databaseHandler = DatabaseHandler.getInstance();
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
    
    @FXML
    void loadBookInfo(ActionEvent event) {
    	String id = this.bookIdTxt.getText();
    	String query = "SELECT * FROM BOOK WHERE id = '" + id + "'; ";
    	
    	ResultSet rs = databaseHandler.executeQuery(query);
    	
    	try {
			while (rs.next()) {
				String name = rs.getString("title");
				String author = rs.getString("author");
				Boolean status = rs.getBoolean("isAvail");
				
				if (status) {
					this.bookStatusLabel.setText("DISPONÍVEL");
				} else {
					this.bookStatusLabel.setText("NÃO DISPONÍVEL");
					this.bookStatusLabel.setFill(Paint.valueOf("#ff0000"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}