import java.io.File;
import java.util.Arrays;


public class RambdaSort {

	private static void sortAndPrint(File[] files) {
		Arrays.sort(files, (first, second)
					-> first.toString().compareTo(second.toString())
				);
		
		for (File file : files)
			System.out.println(file.toString());
	}
	
	public static void main(String[] args) {
		File root = new File("C:\\");
		File[] dirs = root.listFiles(File::isDirectory);
		File[] files = root.listFiles(File::isFile);
		
		sortAndPrint(dirs);
		sortAndPrint(files);

	}

}
