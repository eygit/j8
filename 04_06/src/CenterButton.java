import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CenterButton extends Application {
   public void start(Stage stage) {
      BorderPane pane = new BorderPane();
      Button top = new Button("Top");
      HBox topBox = new HBox(10);
      topBox.getChildren().addAll(top);
      topBox.setAlignment(Pos.CENTER);
      pane.setTop(topBox);
      pane.setLeft(new Button("Left"));
      pane.setCenter(new Button("Center"));
      pane.setRight(new Button("Right"));
      Button bottom = new Button("Bottom");
      HBox buttomBox = new HBox(10);
      buttomBox.getChildren().addAll(bottom);
      buttomBox.setAlignment(Pos.CENTER);
      pane.setBottom(buttomBox);
      stage.setScene(new Scene(pane));
      stage.show();
   }
	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
