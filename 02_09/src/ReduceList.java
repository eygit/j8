import java.util.ArrayList;
import java.util.stream.Stream;


public class ReduceList {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			a.add("a");
		}
		ArrayList<String> b = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			b.add("b");
		}
		ArrayList<String> c = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			c.add("c");
		}
		
		Stream<ArrayList<String>> s = Stream.of(a, b, c);
		
		// 3つの形式のreduceが、引数を３つとるreduece形式のことを言っているのなら、以下は１つなので不適切・・・・
		ArrayList<String> concut = s.reduce((x, y) -> {x.addAll(y); return x;} ).get();
		System.out.println(concut);

	}

}
