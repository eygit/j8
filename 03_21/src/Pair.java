import java.util.function.Function;


public class Pair<T,U> {
	private T t1;
	private T t2;
	public Pair(T t1, T t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	
	Pair<U, U> map(Function<T, U> function) {
		return new Pair<U, U>(function.apply(t1), function.apply(t2));
	}

}
