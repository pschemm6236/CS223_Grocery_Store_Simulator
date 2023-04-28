package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// this is the line where the scene gets loaded in
			Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
			
			stage.setTitle("JavaRUs Store Simulator");
			Image image = new Image("application/giraffe.png");
			stage.getIcons().add(image);
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
