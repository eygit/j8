import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public class CreditShark {

	public static void main(String[] args) throws IOException, InterruptedException {
	      ProcessBuilder builder = new ProcessBuilder(
	    	         "grep", "-o", "[0-9]*", "-r", "/home/<USERNAME>");
	    	      /*builder.redirectInput(Paths.get("~/").toFile());*/
	    	      builder.redirectOutput(Paths.get("identifiers.txt").toFile());
	    	      Process process = builder.start();
	    	      process.waitFor(1, TimeUnit.MINUTES);

	}

}
