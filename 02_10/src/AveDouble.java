import java.util.stream.Stream;


public class AveDouble {

	public static void main(String[] args) {
		Stream<Double> ds = Stream.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 0.5);  // sum 5.0
		Stream<Double> ds2 = ds.limit(10);
		long num = ds.count();
		Double d = ds2.reduce(0.0, (x, y) -> (x+y));
		System.out.println(d/num);

	}

}
