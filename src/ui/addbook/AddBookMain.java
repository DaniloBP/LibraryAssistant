package ui.addbook;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class AddBookMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLAddBook.fxml"));	
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("addBookStyle.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	 
}
