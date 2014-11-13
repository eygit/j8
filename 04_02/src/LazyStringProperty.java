import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LazyStringProperty {
	private String rawText = "";
	private StringProperty text = null;

	public final StringProperty textProperty() {
		if (text == null) {
			System.out.println("Create a new property.");
			text = new SimpleStringProperty(rawText);
		}
		return text;
	}
	public final void setText(String newValue) {
		if (text == null) {
			rawText = newValue;
		} else {
			text.set(newValue);
		}
	}
	public final String getText() {
		if (text == null) {
			return rawText;
		} else {
			return text.get();
		}
	}

	public static void main(String[] args) {
		List<LazyStringProperty> chart = new ArrayList<LazyStringProperty>();
		for (int i = 0; i < 10; i++){
			chart.add(new LazyStringProperty());
		}
		for (int i = 0; i < 10; i++){
			chart.get(i).setText("hoge:" + i);
			chart.get(i).getText();
		}
		System.out.println("↑ここまでのget/setではpropertyが作成されていないこと↑");

		for (int i = 0; i < 10; i++){
			chart.get(i).textProperty();
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
