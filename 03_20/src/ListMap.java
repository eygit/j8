import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class ListMap {
	static <T, U> List<U> map(List<T> lt, Function<T,U> f) {
		List<U> lu = new ArrayList<U>();
		for (T t: lt) {
			lu.add(f.apply(t));
		}
		return lu;
	}
}
