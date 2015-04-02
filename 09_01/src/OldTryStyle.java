import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class OldTryStyle {


	public static void main(String[] args) {

		Exception eStore = null;
		Scanner in = null;
		try {
			in = new Scanner(Paths.get("./alice.txt"));
			PrintWriter out = null;
			try {
				out = new PrintWriter("./out.txt");

				try {
					while (in.hasNext()) {
						out.println(in.next().toLowerCase());
					}
				} catch (IllegalStateException | NoSuchElementException e) {
					// printlnのJavadoc(https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html#println())
					// に、例外スローの記述が見つからない。
					e.printStackTrace();
				}


			} catch (FileNotFoundException | SecurityException  e) {
				eStore = e;
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (Exception e) {
					// AutoCloseableがExceptionスロー
					e.printStackTrace();
					e.initCause(eStore);
					eStore = null;
				}

			}
		} catch (IOException e) {
			eStore = e;
			e.printStackTrace();
		} finally {
			try {
				if (in != null) in.close();
			} catch (Exception e) {
				// AutoCloseableがExceptionスロー
				e.printStackTrace();
				e.initCause(eStore);
				eStore = null;
			}
		}

	}

}
