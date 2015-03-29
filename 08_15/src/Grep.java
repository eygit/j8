import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Grep {
	
	public static void printGrep(String root, String regex) throws IOException {
		try(Stream<Path> stream = Files.walk(Paths.get(root), Integer.MAX_VALUE)){
			  stream.forEach(path -> {
				  try (Stream<String> lineStream = Files.lines(path);) {
					Pattern p = Pattern.compile(regex);
					Predicate<String> pp = p.asPredicate();
					lineStream.filter(pp).forEach(System.out::println);
				} catch (Exception e1) {
					// AccessDeniedExceptionが出るが、、、握りつぶす
				}
			  });
			}

	}

	public static void main(String[] args) throws IOException {
		printGrep("C:\\Program Files\\Java\\jdk1.8.0_11\\src", "^public");
	}

}
