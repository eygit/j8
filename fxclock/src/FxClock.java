import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
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

		// Clock Label設定.
		Label clockLabel = new Label();
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
			clockLabel.setText(clockStringService.getLastValue());
		});
		clockStringService.setPeriod(Duration.seconds(1));
		clockStringService.start();
		clockLabel.setFont(new Font(30));

		// FontFamilyMenu設定.
		List<String> fontFamilies = Font.getFamilies();
		Menu fontFamilyMenu = new Menu("FontFamily");
		for (String fontFamily : fontFamilies) {
			MenuItem fontFamilyItem = new MenuItem(fontFamily);
			fontFamilyItem.setOnAction(e -> clockLabel.setFont(new Font(fontFamily, clockLabel.getFont().getSize())));
			fontFamilyMenu.getItems().add(fontFamilyItem);
		}


		// FontNameMenu設定.
		List<String> fontNames = Font.getFontNames();
		Menu fontNameMenu = new Menu("FontName");
		for (String fontName : fontNames) {
			MenuItem fontNameItem = new MenuItem(fontName);
			fontNameItem.setOnAction(e -> clockLabel.setFont(new Font(fontName, clockLabel.getFont().getSize())));
			fontNameMenu.getItems().add(fontNameItem);
		}

		// FontSizeMenu設定.
		List<Double> fontSiezes = Arrays.asList(8.0, 10.0, 12.0, 14.0, 18.0, 24.0, 32.0, 36.0, 48.0, 72.0);
		Menu fontSizeMenu = new Menu("FontSize");
		for (Double fontSize : fontSiezes) {
			MenuItem fontSizeItem = new MenuItem(Integer.valueOf(fontSize.intValue()).toString());
			fontSizeItem.setOnAction(e -> clockLabel.setFont(new Font(clockLabel.getFont().getName(), fontSize)));
			fontSizeMenu.getItems().add(fontSizeItem);
		}


		// 画面組み立て
		MenuBar mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(fontFamilyMenu, fontNameMenu, fontSizeMenu);
		VBox topContainer = new VBox();
		topContainer.getChildren().addAll(mainMenu, clockLabel);
		BorderPane root = new BorderPane();
		root.setTop(topContainer);

		stage.setScene(new Scene(root));
		stage.setWidth(400);
		stage.setHeight(150);
		stage.setTitle("fx clock");
		stage.show();

	}

	public static void main(String[] args) {
        launch(args);
	}

}
