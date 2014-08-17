package hoge;

import java.io.File;

public class PrintExtFile {

	private static void printExtFile(String pathname, String extname) {
		File root = new File(pathname);
		File[] files = root.listFiles( (dir, name) ->
					{
						String strings[] = name.split("\\.");
						return extname.equals(strings[strings.length - 1]);
					}
				);
		for (File file : files)
			System.out.println(file);
	}

	public static void main(String[] args) {
		String pathname = "C:\\"; // キャプチャされる変数
		String extname = "txt"; // キャプチャされる変数
		printExtFile(pathname, extname);


	}
}
