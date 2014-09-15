import java.util.stream.Stream;


public class InfiniteRandom {

	private static Stream<Long> genLongRandom(long a, long c, double m,	long seed) {
		return Stream.iterate(seed, x -> (long)((a*x + c)%m));
	}

	public static void main(String[] args) {
		long a = 25214903917L;
		long c = 11;
		double m = Math.pow(2, 45);
		long seed = 13;
		Stream<Long> randoms = genLongRandom(a, c, m, seed);
		
		randoms.limit(5).forEach(System.out::println);
	}

}
