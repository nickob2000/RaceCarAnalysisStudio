import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RCASMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(RCASMain.class.getResource("RCASMainView.fxml"));
		ResourceBundle resourceBundle = ResourceBundle.getBundle("RCASResources");
		fxmlLoader.setResources(resourceBundle);

		GridPane mainPane = (GridPane) fxmlLoader.load();
		Scene mainScene = new Scene(mainPane, 800, 600);
		primaryStage.centerOnScreen();
		primaryStage.setTitle(resourceBundle.getString("applicationTitle"));
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(RCASMain.class, args);
	}
}
