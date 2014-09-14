import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamOfInt {

	public static void main(String[] args) {
		int[] values = {1, 4, 9, 16};
		
		Stream<int[]> intStream = Stream.of(values);
		
		// Integer型専用のストリームで生成することもできる。
		IntStream intStream2 = IntStream.of(values);

	}

}
