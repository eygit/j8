import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FxClock extends Application {


	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		VBox topContainer = new VBox();
		MenuBar mainMenu = new MenuBar();
		Menu fontMenu = new Menu("Font");

		List<String> fontFamilies = Font.getFamilies();
		System.out.println(fontFamilies);
		for (String fontFamily : fontFamilies) {
			MenuItem fontFamilyItem = new MenuItem(fontFamily);
			fontMenu.getItems().add(fontFamilyItem);
		}


		List<String> fontNames = Font.getFontNames();
		System.out.println(fontNames);




		Label message = new Label();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);

		ScheduledService<String> clockStringService = new ScheduledService<String>() {

			@Override
			protected Task<String> createTask() {
				return new Task<String>() {

					@Override
					protected String call() throws Exception {
						return LocalDateTime.now().format(dateFormat);
					}

				};
			}
		};
		clockStringService.setOnScheduled( e -> {
			message.setText(clockStringService.getLastValue());
		});
		clockStringService.setPeriod(Duration.seconds(1));
		clockStringService.start();

		message.setFont(new Font(30));



		mainMenu.getMenus().addAll(fontMenu);
		topContainer.getChildren().addAll(mainMenu, message);
		root.setTop(topContainer);

		stage.setScene(new Scene(root));
		stage.setWidth(200);
		stage.setTitle("fx clock");
		stage.show();

	}

	public static void main(String[] args) {
        launch(args);
	}

}
