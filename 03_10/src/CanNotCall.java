import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CanNotCall extends Application {
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

	public static ColorTransformer genColorTransformer(Image in, int band, Color frameColor) {
		return (x, y, c) -> {
			int width = (int) in.getWidth();
			int height = (int) in.getHeight();
			if ((x < band) || (width-band <= x) || (y < band) || (height-band <= y)) {
				return frameColor;
			}
			return c;
		};
	}

	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");

		// 左辺の「UnaryOperator op」の記述では、右辺の型は推論できないため、Object型が推測される。
		// このとき、右辺の式では、Colorを受け取ってColorを返すメソッド参照が記述されているため、
		// 型不一致となる(Object型にはbrighterメソッドはないためコンパイルNG)。
		// なお、対処としては、UnaryOperator<Color> op = Color::brighter; のように型付けすれば良い。
		// または、型安全性に欠けるが、UnaryOperator op2 = c -> ((Color) c).brighter()でも良い。
		UnaryOperator op;
//		op = Color::brighter;

		// composeメソッドは、2つ目のUnaryOperator(引数名before)を先に処理してから1つ目のUnaryOperatorを処理する関数を返す。
		// 2つ目(Color::grayscale)の処理結果はUnaryOperator<Color>型となるため、
		// 1つ目のUnaryOperator総称型とは型が不一致となる。
		// → 推論規則の詰めが甘い気がする・・・。

		// 関数合成では、型推論に合成順序が影響する？ため、
		// ノミナル型であるよりもストラクチャ型である方が望ましいと考えらえる。
//		Image finalImage = transform(image, op.compose(Color::grayscale));




		stage.setScene(new Scene(new HBox(
				new ImageView(image),
				new ImageView(transform(image, genColorTransformer(image, 10, Color.GRAY))),
				new ImageView( transform(image, genColorTransformer(image, 20, Color.BLUE)))
				)));
		stage.show();
	}

	public static void main(String[] args) {
        launch(args);

	}

}
