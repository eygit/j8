import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class SimpleBrowser extends Application {
	public String prevUrl(WebEngine engine) {
		final WebHistory history=engine.getHistory();
		ObservableList<WebHistory.Entry> entryList=history.getEntries();
		int currentIndex=history.getCurrentIndex();
		Platform.runLater( () -> {history.go(-1);});
		return entryList.get(currentIndex > 0 ? currentIndex - 1 : currentIndex).getUrl();
	}


	@Override public void start(Stage stage) {
		String location = "http://localhost:18080/";
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		engine.load(location);

		// 戻るボタン
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> browser.getEngine().load(prevUrl(engine)));

		// URLフィールド
		TextField textField = new TextField(location);
		textField.setOnAction(e -> engine.load(textField.getText()));
//		textField.textProperty().bind(engine.locationProperty()); // バインドすると、フィールドに直接URL入力でページ遷移できなくなる・・・

		VBox box = new VBox(10);
		box.getChildren().addAll(backButton, textField, browser);
		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
