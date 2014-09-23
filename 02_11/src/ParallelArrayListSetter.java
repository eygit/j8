import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ParallelArrayListSetter {


	public static void main(String[] args) {
		final int size = 30;
		ArrayList<Integer> arrayList = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			arrayList.add(100);
		}

		AtomicInteger index = new AtomicInteger();
		Stream<Integer> is = IntStream.range(0, size).boxed().parallel();
		is.forEach(e -> arrayList.set(index.getAndIncrement(), e));

		arrayList.forEach(System.out::println);
	}

}
