import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ReversedLineRW {

	public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./alice.txt"));
        List<String> reversedLines = new ArrayList<String>();
        for (String line : lines) {
        	reversedLines.add(new StringBuffer(line).reverse().toString());
        }
        Files.write(Paths.get("./out.txt"), reversedLines);
        

	}

}
