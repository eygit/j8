import static javafx.beans.binding.Bindings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ObservableClazz extends Application {

	public static <T, R> ObservableValue<R> observe (Function<T, R> f, ObservableValue<T> t) {
		return new ObservableValue<R>() {
			private List<InvalidationListener> invalidationListeners = new ArrayList<InvalidationListener>();
			private List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

			@Override
			public void addListener(InvalidationListener l) {
				System.out.println("InvalidationListener addListener called");
				invalidationListeners.add(l);
			}

			@Override
			public void removeListener(InvalidationListener l) {
				System.out.println("InvalidationListener removeListener called");
				invalidationListeners.remove(l);
			}

			@Override
			public void addListener(ChangeListener<? super R> l) {
				System.out.println("ChangeListener addListener called");
				changeListeners.add(l);
			}
			@Override
			public void removeListener(ChangeListener<? super R> l) {
				System.out.println("ChangeListener removeListener called");
				changeListeners.remove(l);
			}

			@Override
			public R getValue() {


				System.out.println("getValue called");
//				System.out.println(t);
//				for (InvalidationListener l: invalidationListeners) {
//					l.invalidated(t);
//				}
//				for (ChangeListener l: changeListeners) {
//					l.changed(t, 10, 90);
//				}

				//TODO: ここでどのようにラムダ式を活用するのかよくわかっていない。
				return f.apply(t.getValue());
			}

		};
	}
	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
		return null;
	}

   public void start(Stage stage) {
      Button smaller = new Button("Smaller");
      Button larger = new Button("Larger");
      Rectangle gauge = new Rectangle(0, 5, 50, 15);
      Rectangle outline = new Rectangle(0, 5, 100, 15);
      outline.setFill(null);
      outline.setStroke(Color.BLACK);
      Pane pane = new Pane();
      pane.getChildren().addAll(gauge, outline);
      smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
      larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
      smaller.disableProperty().bind(lessThanOrEqual(gauge.widthProperty(), 0));
//      larger.disableProperty().bind(greaterThanOrEqual(gauge.widthProperty(), 100));
      larger.disableProperty().bind(observe(t -> t.intValue() >= 100, gauge.widthProperty() ) );


      HBox box = new HBox(10);
      box.getChildren().addAll(smaller, pane, larger);
      Scene scene = new Scene(box);
      stage.setScene(scene);
      stage.show();
   }

	// 書籍では不要と記載があるが、eclipseからはmain文がないと起動できないため追記。
	public static void main(String[] args) {
		launch(args);
	}

}
