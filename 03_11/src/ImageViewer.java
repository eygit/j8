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

public class ImageViewer extends Application {
   public static Image transform(Image in, UnaryOperator<Color> f) {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      WritableImage out = new WritableImage(
         width, height);
      for (int x = 0; x < width; x++)
         for (int y = 0; y < height; y++)
            out.getPixelWriter().setColor(x, y,
               f.apply(in.getPixelReader().getColor(x, y)));
      return out;
   }

   public static Image transform(Image in, ColorTransformer f) {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      WritableImage out = new WritableImage(
         width, height);
      for (int x = 0; x < width; x++)
         for (int y = 0; y < height; y++)
            out.getPixelWriter().setColor(x, y,
               f.apply(x, y, in.getPixelReader().getColor(x, y)));
      return out;
   }

   /**
    * ColorTransformerを合成できるstaticメソッドの実装。
    */
   public static ColorTransformer compose(ColorTransformer ct1, ColorTransformer ct2) {
	   return (x, y, colorAtXY) -> ct2.apply(x, y, ct1.apply(x, y, colorAtXY));
   }

   /**
    * UnaryOperator<Color>から、x座標とy座標を無視するColorTransformerを生成するstaticメソッド実装。
    */
   public static ColorTransformer genColorTransformer(UnaryOperator<Color> f) {
	   return (x, y, colorAtXY) -> f.apply(colorAtXY);
   }


   public void start(Stage stage) {
      Image image = new Image("queen-mary.png");
      /*
       * 変換によって明るくなった画像に灰色の枠を追加するために実装したメソッドを利用する。
       */
      Image imageAns = transform(image, compose(
    		  genColorTransformer(Color::brighter)
    	        		 ,
    		  (x, y, c) -> x < 10 || x > image.getWidth() - 10
    	         || y < 10 || y > image.getHeight() - 10 ? Color.GRAY : c
    		  ));
      

      stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(imageAns))));
      stage.show();
   }

	public static void main(String[] args) {
        launch(args);
	}

}
