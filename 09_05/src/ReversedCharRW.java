import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReversedCharRW {

	public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        String reversed = new StringBuffer(contents).reverse().toString();
        Files.write(Paths.get("./out.txt"), reversed.getBytes(StandardCharsets.UTF_8));
        

	}

}
