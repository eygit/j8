import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class NamedCG {

	public static void main(String[] args) throws IOException {
		String filename = "./alice.txt";
		String regex = ("(?<city>[\\p{L} ]+), \\s*(?<state>[A-Z]{2})"); // 郵便番号の追加.
		
        String contents = new String(Files.readAllBytes(
                Paths.get(filename)), StandardCharsets.UTF_8);
		Stream<String> s = Pattern.compile(regex).splitAsStream(contents);
		s.forEach(System.out::printf);
		
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        // この先が不明. filterを利用する？
        
	}

}
