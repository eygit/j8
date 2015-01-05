import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;


public class LongAccumulatorSample {

	public static void main(String[] args) {
		LongAccumulator minAccumulator = new LongAccumulator(Long::min, 0);
		LongAccumulator maxAccumulator = new LongAccumulator(Long::max, 0);

		int numberSize = 10000;
		Random random = new Random(2015);
		for (int i = 0; i < numberSize; i++) {
			long value = random.nextLong();
			minAccumulator.accumulate(value);
			maxAccumulator.accumulate(value);
		}
		System.out.println("min: " + minAccumulator.get());
		System.out.println("max: " + maxAccumulator.get());

	}

}
