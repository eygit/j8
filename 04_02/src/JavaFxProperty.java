import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class JavaFxProperty extends Application {

	@Override
	public void start(Stage stage) {
		String initialString = "Hello, JavaFX!";
		Label message = new Label(initialString);
		message.setFont(new Font(100));
		TextField textField = new TextField(initialString);
		textField.setOnAction(e -> message.setText(textField.getText()));

		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
			      new PieChart.Data("レタス, 128円", 128),
			      new PieChart.Data("トマト, 100円", 100),
			      new PieChart.Data("じゃがいも, 158円", 158),
			      new PieChart.Data("舞茸,95円", 95)
			    );
		Chart chart = new PieChart(ol);

		VBox root = new VBox();
		root.getChildren().addAll(message, textField, chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
