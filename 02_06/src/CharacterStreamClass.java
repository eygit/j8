import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CharacterStreamClass {

	public static Stream<Character> orgCharacterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray()) {
			result.add(c);
		}
		return result.stream();
	}
	public static void main(String[] args) {
		String s ="abcdefghijklmnopqrstuvwxyz";
//		orgCharacterStream(s).forEach(System.out::println);
		IntStream.range(0, s.length()).map(s::charAt).forEach(c -> System.out.println((char)c));
	}

}
