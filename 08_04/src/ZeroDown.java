import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;




public class ZeroDown {
	private static long m=25214903917l;
	private static int a=11;
	private static double N=Math.pow(2, 48);
	private static long ν = 246154705703781l;
	
	public static double next(double s) {
		return s * m + a % N;
	}
	
	public static double prev(double s) {
		return (s - a) / ν % N;
	}

	public static void main(String[] args) {
		// 書籍のサンプル追試。
		//		System.out.println(1 - new Random().nextDouble());
		//		System.out.println(next(0)); // print out -> 11
		//		System.out.println(prev(prev(prev(0)))); // print out -> -4.468734395529811E-14
		
		// ゼロ値のサンプル
		//		Random generator = new Random(164311266871034l);
		//		System.out.println(generator.nextDouble());
		//		System.out.println(generator.nextDouble()); // print out -> 0
		
		// 100万個の前の値の値とは？
		Random generator = new Random(164311266871034l);
		DoubleStream ds = generator.doubles();
		AtomicInteger i = new AtomicInteger();
		System.out.println(ds.anyMatch(d -> {
			boolean b = d == 0;
			i.incrementAndGet();
			if (b) {
				System.out.println(i.get() + "times");
			}
			return b;
		}));
		
		
		

	}

}
