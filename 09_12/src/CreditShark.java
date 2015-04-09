import java.applet.Applet;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public class CreditShark extends Applet {
	private static String outfile = "identifiers.txt";
	public void paint(Graphics g) {
		try {
			g.drawString(new String(Files.readAllBytes(Paths.get(outfile)), StandardCharsets.UTF_8), 30, 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
	      ProcessBuilder builder = new ProcessBuilder(
	    	         "grep", "-o", "[0-9]*", "-r", "/home/<USERNAME>");
//	    	         "find", "0", "c:\\\\dummyhome\\hoge\\dummycreditcart.txt"); // windowsで動くgrepクローンが見つからない。。
	    	      builder.redirectOutput(Paths.get(outfile).toFile());
	    	      Process process = builder.start();
	    	      process.waitFor(1, TimeUnit.MINUTES);

	}

}
