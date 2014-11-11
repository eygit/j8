import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class HelloJavaFx extends Application {

	@Override
	public void start(Stage stage) {
		String initialString = "Hello, JavaFX!";
		Label message = new Label(initialString);
		message.setFont(new Font(100));
		TextField textField = new TextField(initialString);
		textField.setOnAction(e -> message.setText(textField.getText()));
		VBox root = new VBox();
		root.getChildren().addAll(message, textField);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
