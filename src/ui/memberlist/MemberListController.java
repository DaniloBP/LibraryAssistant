package ui.memberlist;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MemberListController implements Initializable {

	@FXML
    private AnchorPane rootPane;
	
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> nameColumn; 
    @FXML
    private TableColumn<Member, String> idColumn;
    @FXML
    private TableColumn<Member, String> phoneColumn;
    @FXML
    private TableColumn<Member, String> emailColumn;     
    
    private ObservableList<Member> list = FXCollections.observableArrayList();
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initColumns();
		loadData();
	}
	
	private void initColumns() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));	
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));	
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));	
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));		
	}
	
	private void loadData() {
		
		DatabaseHandler handler = DatabaseHandler.getInstance();
		
		String query = "SELECT * FROM MEMBER";   
		
    	ResultSet rs = handler.executeQuery(query);
    	
    	try {
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");				
				
				list.add( new Member(name, id, phone, email) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tableView.getItems().setAll(list);
	}
	

	public static class Member {
		
		private final SimpleStringProperty name;
		private final SimpleStringProperty id;
		private final SimpleStringProperty phone;
		private final SimpleStringProperty email;
		
		public Member (String name, String id, String phone, String email) {			
			
			super();
			this.name = new SimpleStringProperty(name);
			this.id = new SimpleStringProperty(id);
			this.phone = new SimpleStringProperty(phone);
			this.email = new SimpleStringProperty(email);
		}

		public String getName() {
			return name.get();
		}

		public String getId() {
			return id.get();
		}

		public String getPhone() {
			return phone.get();
		}

		public String getEmail() { 
			return email.get();
		}
	}

}
