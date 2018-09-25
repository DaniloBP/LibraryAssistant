package ui.booklist;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BookListController implements Initializable {

	@FXML
    private AnchorPane rootPane;
	
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    @FXML
    private TableColumn<Book, Boolean> availabilityColumn;
    
    
    private ObservableList<Book> list = FXCollections.observableArrayList();
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initColumns();
		loadData();
	}
	
	private void initColumns() {
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));	
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));	
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));	
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));		
		availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));	
	}
	
	private void loadData() {
		
		DatabaseHandler handler = DatabaseHandler.getInstance();
		
		String query = "SELECT * FROM BOOK";   
		
    	ResultSet rs = handler.executeQuery(query);
    	
    	try {
			while (rs.next()) {
				String id = rs.getString("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");				
				Boolean availability = rs.getBoolean("isAvail");
				
				list.add( new Book(title, id, author, publisher, availability) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tableView.getItems().setAll(list);
	}
	

	public static class Book {
		
		private final SimpleStringProperty title;
		private final SimpleStringProperty id;
		private final SimpleStringProperty author;
		private final SimpleStringProperty publisher;
		private final SimpleBooleanProperty availability;		
		
		public Book(String title, String id, String author, String publisher, Boolean availability) {			
			
			super();
			this.title = new SimpleStringProperty(title);
			this.id = new SimpleStringProperty(id);
			this.author = new SimpleStringProperty(author);
			this.publisher = new SimpleStringProperty(publisher);
			this.availability = new SimpleBooleanProperty(availability);
		}

		public String getTitle() {
			return title.get();
		}


		public String getId() {
			return id.get();
		}


		public String getAuthor() {
			return author.get();
		}


		public String getPublisher() {
			return publisher.get();
		}


		public Boolean getAvailability() {
			return availability.get();
		}
		
	}

}
