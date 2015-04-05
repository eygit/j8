import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LesserWget {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.yahoo.co.jp/index.html");
		InputStream ins = url.openStream();
		Files.copy(ins, Paths.get("./out.txt"));

	}

}
