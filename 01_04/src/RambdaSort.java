import java.io.File;


public class RambdaSort {

	public static void main(String[] args) {
		File root = new File("C:\\");
		File[] dirs = root.listFiles(File::isDirectory);
		File[] files = root.listFiles(File::isFile);

	}

}
