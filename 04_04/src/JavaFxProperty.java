import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class JavaFxProperty extends Application {

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle();
		Group root = new Group();
		root.getChildren().addAll(circle);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		circle.radiusProperty().bind(Bindings.divide(scene.heightProperty(), 2));

		stage.show();
	}

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
