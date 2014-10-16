import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ImageViewer extends Application {

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
		}
		return out;
	}

	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image image2 = transform(image, (x, y, c) -> {
			int width = (int) image.getWidth();
			int height = (int) image.getHeight();
			int band = 10;
			Color frameColor = Color.GRAY;
			if ((x < band) || (width-band <= x) || (y < band) || (height-band <= y)) {
				return frameColor;
			}
			return c;
		});

		stage.setScene(new Scene(new HBox(
				new ImageView(image),
				new ImageView(image2)
				)));
		stage.show();
	}

    public static void main(String... args) {
        launch(args);
    }
}
