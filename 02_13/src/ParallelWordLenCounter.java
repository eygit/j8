import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class ParallelWordLenCounter {

	public static void main(String[] args) {
		String[] wordArray = {"a", "bb", "ccc", "d", "ee"};
		Stream<String> parallelwords = Stream.of(wordArray).parallel();

		Map<Integer, List<String>> map = parallelwords.collect(Collectors.groupingBy(s -> s.length()));
		System.out.println(map);
	}

}
