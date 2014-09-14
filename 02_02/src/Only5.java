import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Only5 {

	public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

//        long count = words.stream().filter(w -> w.length() > 12).count();
//        System.out.println(count);
        long c2 = words.stream().filter(w -> {
        		if (w.length() > 12) {
        			System.out.println(w);
        			return true;
        		} else {
        			return false;
        		}
        	}).limit(5).count();
        System.out.println(c2);
        // answer 33

  
	}

}
