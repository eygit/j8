import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LazyDefaultStringProperty {
	// デフォルトから変化しないため、クラスフィールとしてデフォルトを保持する。
	private static final String defaultText = "defaultString";
	private StringProperty text = null;

	public final StringProperty textProperty() {
		if (text == null) {
			System.out.println("Create a new property.");
			text = new SimpleStringProperty(defaultText);
		}
		return text;
	}
	public final void setText(String newValue) {
		if (text == null) {
			System.out.println("Create a new property(set case).");
			text = new SimpleStringProperty(defaultText);
		} else {
			text.set(newValue);
		}
	}
	public final String getText() {
		if (text == null) {
			return defaultText;
		} else {
			return text.get();
		}
	}

	public static void main(String[] args) {
		List<LazyDefaultStringProperty> chart = new ArrayList<LazyDefaultStringProperty>();
		for (int i = 0; i < 10; i++){
			chart.add(new LazyDefaultStringProperty());
		}
		for (int i = 0; i < 10; i++){
			chart.get(i).getText();
		}
		System.out.println("↑ここまでのget/setではpropertyが作成されていないこと↑");

		for (int i = 0; i < 5; i++){
			chart.get(i).textProperty();
		}
		for (int i = 5; i < 10; i++){
			chart.get(i).setText("hoge:" + i);
		}
		System.out.println("↓ここまででpropertyが作成しおわり、以降のget/set/getPropertyではプロパティ作成されないこと↓");
		for (int i = 0; i < 10; i++){
			chart.get(i).setText("hoge:" + i);
			chart.get(i).getText();
			chart.get(i).textProperty();
			chart.get(i).textProperty().toString();
			chart.get(i).textProperty().setValue("foo:" + i);
		}

	}


}
