package hoge;

import java.io.File;

public class PrintExtFile {

	// extnameがキャプチャされる変数
	private static void printExtFile(String pathname, String extname) {
		File root = new File(pathname);
		String[] filenames = root.list( (dir, name) -> {
			String strings[] = name.split("\\.");
			return extname.equals(strings[strings.length - 1]);
		});
		for (String filename : filenames)
			System.out.println(filename);
	}

	public static void main(String[] args) {
		String pathname = "C:\\";
		String extname = "log";
		printExtFile(pathname, extname);


	}
}