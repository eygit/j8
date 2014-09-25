import java.io.File;
import java.util.Arrays;


public class RambdaSort {

	private static void sortAndPrint(File[] files) {
		Arrays.sort(files, (first, second) -> {
			boolean fd = first.isDirectory();
			boolean sd = second.isDirectory();
			if (fd) {
				if (sd) {
					return first.toString().compareTo(second.toString());
				} else {
					return -1;
				}
			} else {
				if (sd) {
					return 1;
				} else {
					return first.toString().compareTo(second.toString());
				}
			}
		});

		for (File file : files)
			System.out.println(file.toString());
	}

	public static void main(String[] args) {
		File root = new File("C:\\");
		sortAndPrint(root.listFiles());
	}

}
