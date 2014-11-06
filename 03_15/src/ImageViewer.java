import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class LatentImage {
   private Image in;
   private List<UnaryOperator<Color>> pendingOperations;

   public static LatentImage from(Image in) {
      LatentImage result = new LatentImage();
      result.in = in;
      result.pendingOperations = new ArrayList<>();
      return result;
   }

   LatentImage transform(UnaryOperator<Color> f) {
      pendingOperations.add(f);
      return this;
   }

   public Image toParallelImage() {
	  int n = Runtime.getRuntime().availableProcessors();
	  int width = (int) in.getWidth();
	  int height = (int) in.getHeight();
	  Color[][] out = new Color[height][width];
	  try{
	      ExecutorService pool = Executors.newCachedThreadPool();
		  for (int i = 0; i < n; i++) {
	          int fromY = i * height / n;
	          int toY = (i + 1) * height / n;
	          pool.submit(() -> {
	                System.out.printf("%s %d...%d\n", Thread.currentThread(), fromY, toY - 1);
	                for (int x = 0; x < width; x++)
	                   for (int y = fromY; y < toY; y++) {
	                       for (UnaryOperator<Color> f : pendingOperations) {
	                    	   out[y][x] = f.apply(in.getPixelReader().getColor(x, y));
	                       }
	                   }
	             });
	       }
	       pool.shutdown();
	       pool.awaitTermination(1, TimeUnit.HOURS);
	  } catch (InterruptedException ex) {
	      ex.printStackTrace();
	  }

	  WritableImage outImage = new WritableImage(width, height);
	  for (int x = 0; x < width; x++)
	     for (int y = 0; y < height; y++) {
	        outImage.getPixelWriter().setColor(x, y, out[y][x]);
	     }
	  return outImage;
   }
}

public class ImageViewer extends Application {
   public void start(Stage stage) {
      Image image = new Image("eiffel-tower.jpg");
      Image finalImage = LatentImage.from(image)
         .transform(Color::brighter).transform(Color::grayscale)
         .toParallelImage();
      stage.setScene(new Scene(new HBox(
         new ImageView(image),
         new ImageView(finalImage))));
      stage.show();
   }

	public static void main(String[] args) {
        launch(args);
	}
}
