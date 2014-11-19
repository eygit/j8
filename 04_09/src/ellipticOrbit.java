import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ellipticOrbit extends Application {
	private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
		ArcTo arcTo = new ArcTo();
		arcTo.setX(centerX - radiusX + 1);
		arcTo.setY(centerY - radiusY);
		arcTo.setSweepFlag(false);
		arcTo.setLargeArcFlag(true);
		arcTo.setRadiusX(radiusX);
		arcTo.setRadiusY(radiusY);
		arcTo.setXAxisRotation(rotate);
		Path path = new Path();
		path.getElements().add(new MoveTo(centerX - radiusX, centerY - radiusY));
		path.getElements().add(arcTo);
		return path;
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle (0, 0, 10, 10);
		rect.setArcHeight(10);
		rect.setArcWidth(10);
		rect.setFill(Color.BLUE);

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(2000));
		pathTransition.setNode(rect);
		pathTransition.setPath(createEllipsePath(200, 200, 50, 100, 45));
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(false);
		pathTransition.play();

		VBox pane = new VBox(10);
		pane.setPrefWidth(300);
		pane.setPrefHeight(300);
		pane.setPadding(new Insets(10));
		pane.getChildren().addAll(rect);

		stage.setScene(new Scene(pane));
		stage.show();
	}

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}
}



