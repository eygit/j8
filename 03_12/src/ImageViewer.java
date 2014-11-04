import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
   Color apply(int x, int y, Color colorAtXY);
}

class LatentImage {
   private Image in;
   private List<ColorTransformer> pendingOperations;

   public static LatentImage from(Image in) {
      LatentImage result = new LatentImage();
      result.in = in;
      result.pendingOperations = new ArrayList<>();
      return result;
   }

   LatentImage transform(UnaryOperator<Color> f) {
	   // UnaryOperator<Color>をColorTransformerへ適用させる。
	   pendingOperations.add(genColorTransformer(f));
	   return this;
   }

   LatentImage transform(ColorTransformer ct) {
	      pendingOperations.add(ct);
	      return this;
	   }

   /**
    * UnaryOperator<Color>から、x座標とy座標を無視するColorTransformerを生成するstaticメソッド実装。
    * 03_11の実装
    */
   public static ColorTransformer genColorTransformer(UnaryOperator<Color> f) {
	   return (x, y, colorAtXY) -> f.apply(colorAtXY);
   }



   public Image toImage() {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      WritableImage out = new WritableImage(width, height);
      for (int x = 0; x < width; x++)
         for (int y = 0; y < height; y++) {
            Color c = in.getPixelReader().getColor(x, y);
            for (ColorTransformer f : pendingOperations) c = f.apply(x, y, c);
            out.getPixelWriter().setColor(x, y, c);
         }
      return out;
   }
}

public class ImageViewer extends Application {
   public void start(Stage stage) {
      Image image = new Image("eiffel-tower.jpg");
      Image finalImage = LatentImage.from(image)
         .transform(Color::brighter).transform(Color::grayscale)
         .toImage();
      stage.setScene(new Scene(new HBox(
         new ImageView(image),
         new ImageView(finalImage))));
      stage.show();
   }

	public static void main(String[] args) {
        launch(args);
	}

}
