import java.util.stream.IntStream;


public class CharacterStreamClass {

	public static void main(String[] args) {
		String s ="abcdefghijklmnopqrstuvwxyz";
		IntStream.range(0, s.length()).map(s::charAt).forEach(c -> System.out.print((char)c + ", "));
	}

}
