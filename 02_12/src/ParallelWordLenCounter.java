import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;



public class ParallelWordLenCounter {

	public static void main(String[] args) {
		String[] wordArray = {"a", "bb", "ccc", "d", "ee"};
		Stream<String> parallelwords = Stream.of(wordArray).parallel();

		AtomicInteger[] shortWords = new AtomicInteger[12];
		for (int i = 0; i < 12; i++){
			shortWords[i] = new AtomicInteger();
		}

		parallelwords.forEach(s -> {
			if (s.length() < 12) shortWords[s.length()].getAndIncrement();
		});

		System.out.println(Arrays.asList(shortWords));



	}

}
