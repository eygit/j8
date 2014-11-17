import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFXML extends Application {
   public void start(Stage stage) {
      try {
         Parent root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
         stage.setScene(new Scene(root));
         stage.show();
      } catch (IOException ex) {
         ex.printStackTrace();
         System.exit(0);
      }
   }
	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}

