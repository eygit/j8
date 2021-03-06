import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	// 前段の画素ではなく画像を入力とするよう変更した。
	Color apply(int x, int y, PixelReader reader);
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
	   pendingOperations.add(genColorTransformer(f));
	   return this;
   }

   LatentImage transform(ColorTransformer ct) {
	      pendingOperations.add(ct);
	      return this;
	   }

   public static ColorTransformer genColorTransformer(UnaryOperator<Color> f) {
	   // 画像からピクセルを拾うように変更した。
	   return (x, y, reader) -> f.apply(reader.getColor(x, y));
   }



   public Image toImage() {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      WritableImage out = new WritableImage(width, height);
      for (int x = 0; x < width; x++) {
	         for (int y = 0; y < height; y++) {
	        	 out.getPixelWriter().setColor(x, y, in.getPixelReader().getColor(x, y));
	         }
      }

      // 前段の処理を全ピクセルに対して演算してから後段の処理を実行するように処理順序を入れ替えた。
      for (ColorTransformer f : pendingOperations) {
    	  WritableImage prevIn = out;
    	  out = new WritableImage(width, height);
	      for (int x = 0; x < width; x++) {
	         for (int y = 0; y < height; y++) {

	        	 // TODO: 問題文で、リーダーはキャッシュを調べ・・・がなければリーダーを構築するの意図が理解できていない。。。
	            Color c = f.apply(x, y, prevIn.getPixelReader());
	            out.getPixelWriter().setColor(x, y, c);
	         }
	      }
      }
      return out;
   }
}

public class ImageViewer extends Application {
   public void start(Stage stage) {
      Image image = new Image("eiffel-tower.jpg");

      // ぼやけ検出
      ColorTransformer diffusingFilter =  (x, y, reader) -> {
     	 Color colors[] = new Color[9];
     	 int xw = x == 0 ? x : x -1;
     	 int xe = x == image.getWidth()  -2 ? x + 1 : x; // ラムダの中でImageクラスのオブジェクトをキャプチャしているのがもやもやする。
     	 int yn = y == 0 ? y : y -1;
     	 int ys = y == image.getHeight() -2 ? y + 1 : y;
     	 colors[0] = reader.getColor(xw, yn);
     	 colors[1] = reader.getColor(xw, y );
     	 colors[2] = reader.getColor(xw, ys);
     	 colors[3] = reader.getColor(x  , yn);
     	 colors[4] = reader.getColor(x  , y );
     	 colors[5] = reader.getColor(x  , ys);
     	 colors[6] = reader.getColor(xe, yn);
     	 colors[7] = reader.getColor(xe, y );
     	 colors[8] = reader.getColor(xe, ys);
     	 double r=0;
     	 double g=0;
     	 double b=0;
     	 for (Color oldc: colors) {
     		 r += oldc.getRed();
     		 g += oldc.getGreen();
     		 b += oldc.getBlue();
     	 }
     	 r /= colors.length;
     	 g /= colors.length;
     	 b /= colors.length;
     	 return Color.rgb((int)(r*255), (int)(g*255), (int)(b*255));
      };
      Image diffusingImage = LatentImage.from(image)
    	         .transform(diffusingFilter)
    	         .toImage();

      // エッジ検出
      ColorTransformer edgeFilter =  (x, y, reader) -> {
      	 Color colors[] = new Color[9];
      	 int xw = x == 0 ? x : x -1;
      	 int xe = x == image.getWidth()  -2 ? x + 1 : x;
      	 int yn = y == 0 ? y : y -1;
      	 int ys = y == image.getHeight() -2 ? y + 1 : y;
      	 colors[0] = reader.getColor(xw, yn);
      	 colors[1] = reader.getColor(xw, y );
      	 colors[2] = reader.getColor(xw, ys);
      	 colors[3] = reader.getColor(x  , yn);
      	 colors[4] = reader.getColor(x  , y );
      	 colors[5] = reader.getColor(x  , ys);
      	 colors[6] = reader.getColor(xe, yn);
      	 colors[7] = reader.getColor(xe, y );
      	 colors[8] = reader.getColor(xe, ys);
      	 double r=4*(colors[4].getRed()) - colors[1].getRed() - colors[3].getRed() - colors[5].getRed() - colors[7].getRed();
      	 double g=4*(colors[4].getGreen()) - colors[1].getGreen() - colors[3].getGreen() - colors[5].getGreen() - colors[7].getGreen();
      	 double b=4*(colors[4].getBlue()) - colors[1].getBlue() - colors[3].getBlue() - colors[5].getBlue() - colors[7].getBlue();
      	 r = r < 0 ? 0 : r;
      	 r = r > 1 ? 1 : r;
      	 g = g < 0 ? 0 : g;
      	 g = g > 1 ? 1 : g;
      	 b = b < 0 ? 0 : b;
      	 b = b > 1 ? 1 : b;
      	 return Color.rgb((int)(r*255), (int)(g*255), (int)(b*255));
       };
       Image edgeImage = LatentImage.from(image)
     	         .transform(edgeFilter)
     	         .toImage();

       // ぼやけ処理してからエッジ検出
       Image finalImage = LatentImage.from(image)
    		   .transform(diffusingFilter)
    		   .transform(edgeFilter)
    		   .toImage();


      stage.setScene(new Scene(new HBox(
         new ImageView(image),
         new ImageView(diffusingImage),
         new ImageView(edgeImage),
         new ImageView(finalImage))));
      stage.show();
   }

	public static void main(String[] args) {
        launch(args);
	}

}
